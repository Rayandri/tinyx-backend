package com.epita.service;

import com.epita.controller.contracts.PostContract;
import com.epita.repository.TimelineRepository;
import com.epita.repository.entity.Timeline;
import com.epita.repository.entity.TimelineEntry;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserTimelineService {

    @Inject
    TimelineRepository timelineRepository;

    public Timeline getUserTimeline(UUID userId) {
        Optional<Timeline> optionalTimeline = timelineRepository.findByUserId(userId);
        return optionalTimeline.orElseGet(() -> {
            Timeline newTimeline = new Timeline();
            newTimeline.setUserId(userId);
            newTimeline.setId(userId);
            timelineRepository.saveTimeline(newTimeline);
            return newTimeline;
        });
    }

    public Timeline getUserTimeline(UUID userId, int page, int size, Date fromDate, Date toDate) {
        Timeline timeline = getUserTimeline(userId);

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

        Optional<Timeline> optionalTimeline = timelineRepository.findByUserId(message.getAuthor());
        Timeline timeline = optionalTimeline.orElseGet(() -> {
            Log .info("Creating new timeline for user: " + message.getAuthor());
            Timeline newTimeline = new Timeline();
            newTimeline.userId = message.getAuthor();
            newTimeline.id = message.getAuthor();
            return newTimeline;
        });

        Log.info("Timeline for user: " + message.getAuthor() + " found: " + timeline.getId());

        switch (message.getAction()) {
            case CREATE, LIKED:
                Log.info("Adding post to timeline: " + message.getId());
                timelineRepository.addOrUpdateTimelineEntry(timeline, message.getId(), message.getUpdated_at());
                break;
            case DELETE:
                Log.info("Removing post from timeline: " + message.getId());
                timelineRepository.removeTimelineEntry(timeline, message.getId());
                break;
            case UNLIKED:
                Log.info("Removing post from timeline: " + message.getId());
                // Pour un unlike, on retire le post de la timeline sauf si c'est le post de l'utilisateur lui-même
                if (!timeline.getUserId().equals(message.getAuthor())) {
                    timelineRepository.removeTimelineEntry(timeline, message.getId());
                }
                break;
            default:
                Log.warn("Unknown action: " + message.getAction());
                break;
        }
    }

    public List<Timeline> getAllTimelines() {
        List<Timeline> timelines = timelineRepository.listAll();
        if (timelines.isEmpty()) {
            Log.info("No timelines found");
            return Collections.emptyList();
        }
        return timelines;
    }
}
