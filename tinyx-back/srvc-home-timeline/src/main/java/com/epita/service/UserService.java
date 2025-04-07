package com.epita.service;

import com.epita.controller.contracts.PostContract;
import com.epita.controller.contracts.UserContract;
import com.epita.repository.RepoSocialRestClient;
import com.epita.repository.TimelineRepository;
import com.epita.repository.UserTimelineRestClient;
import com.epita.repository.entity.FollowEntry;
import com.epita.repository.entity.Timeline;
import com.epita.repository.entity.TimelineEntry;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class UserService {
    @Inject
    @RestClient
    UserTimelineRestClient userTimelineRestClient;

    @Inject
    @RestClient
    RepoSocialRestClient repoSocialRestClient;

    @Inject
    TimelineRepository timelineRepository;

    public Timeline getHomeTimeline(UUID userId) {
        Log.info("Get home timeline for user: " + userId);

        return timelineRepository.findByUserId(userId);
    }

    public Timeline getHomeTimeline(UUID userId, int page, int size, Date fromDate, Date toDate) {
        Timeline timeline = getHomeTimeline(userId);

        // Filtrage
        List<TimelineEntry> filteredEntries = timeline.getEntries().stream()
                .filter(entry -> (fromDate == null || !entry.getTimestamp().before(fromDate))
                        && (toDate == null || !entry.getTimestamp().after(toDate)))
                .sorted(Comparator.comparing(TimelineEntry::getTimestamp))
                .collect(Collectors.toList());

        // Pagination
        int start = page * size;
        int end = Math.min(start + size, filteredEntries.size());
        List<TimelineEntry> pagedEntries = new ArrayList<>();
        if (start < filteredEntries.size()) {
            pagedEntries = filteredEntries.subList(start, end);
        }

        // On crée une nouvelle
        Timeline pagedTimeline = new Timeline();
        pagedTimeline.setId(timeline.getId());
        pagedTimeline.setUserId(timeline.getUserId());
        pagedTimeline.setEntries(pagedEntries);

        return pagedTimeline;
    }


    public void newUpdate(PostContract message) {
        Log.info("Update for post action: " + message.getAction() + " for user: " + message.getAuthor());

        Timeline timeline = timelineRepository.findByUserId(message.getAuthor());

        updateTimeline(message, timeline);

        List<FollowEntry> followers = repoSocialRestClient.getFollowers(message.getAuthor());
        for (FollowEntry follower : followers) {
            Timeline followerTimeline = timelineRepository.findByUserId(follower.getFollowerId());
            if (followerTimeline != null) {
                updateTimeline(message, followerTimeline);
            }
        }
    }

    private void updateTimeline(PostContract message, Timeline followerTimeline) {
        switch (message.getAction()) {
            case CREATE, LIKED:
                timelineRepository.addOrUpdateTimelineEntry(followerTimeline, message.getId(), message.getUpdated_at());
                break;
            case DELETE:
                timelineRepository.removeTimelineEntry(followerTimeline, message.getId());
                break;
            case UNLIKED:
                // Pour un unlike, on retire le post de la timeline sauf si c'est le post de l'utilisateur lui-même
                if (!followerTimeline.getUserId().equals(message.getAuthor())) {
                    timelineRepository.removeTimelineEntry(followerTimeline, message.getId());
                }
                break;
        }
    }

    public void newUpdate(UserContract userPublish) {
        Log.info("Update for user action: " + userPublish.getUser_action()
                + " for followed user: " + userPublish.getId()
                + " and follower: " + userPublish.getFollowers());

        UUID followerId = userPublish.getFollowers().get(0);
        Timeline timeline = timelineRepository.findByUserId(followerId);

        switch (userPublish.getUser_action()) {
            case FOLLOW:
                // quand un follower s'abonne à un utilisateur, on ajoute les entrées de la timeline de l'utilisateur
                Timeline userTimeline = userTimelineRestClient.getUserTimeline(userPublish.getId());
                List<TimelineEntry> entries = userTimeline.getEntries();
                for (TimelineEntry entry : entries) {
                    boolean exists = timeline.getEntries().stream().anyMatch(e -> e.getPostId().equals(entry.getPostId()));
                    if (!exists) {
                        timeline.getEntries().add(new TimelineEntry(entry.getPostId(), entry.getTimestamp()));
                    }
                }
                timeline.getEntries().sort(Comparator.comparing(TimelineEntry::getTimestamp));
                timelineRepository.persistOrUpdate(timeline);
                timelineRepository.addOrUpdateTimelineEntry(timeline, userPublish.getId(), new Date());
                break;

            case UNFOLLOW:
                // quand un follower se désabonne d'un utilisateur, on retire les entrées de la timeline
                List<TimelineEntry> entriesToRemove = userTimelineRestClient.getUserTimeline(userPublish.getId()).getEntries();
                for (TimelineEntry entry : entriesToRemove) {
                    timeline.getEntries().removeIf(e -> e.getPostId().equals(entry.getPostId()));
                }
                timelineRepository.persistOrUpdate(timeline);
                timelineRepository.removeTimelineEntry(timeline, userPublish.getId());
                break;
        }
    }
}
