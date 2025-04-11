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
            Timeline newTimeline = new Timeline();
            newTimeline.userId = message.getAuthor();
            newTimeline.id = message.getAuthor();
            return newTimeline;
        });

        switch (message.getAction()) {
            case CREATE, LIKED:
                timelineRepository.addOrUpdateTimelineEntry(timeline, message.getId(), message.getUpdated_at());
                break;
            case DELETE:
                timelineRepository.removeTimelineEntry(timeline, message.getId());
                break;
            case UNLIKED:
                timelineRepository.removeTimelineEntry(timeline, message.getId());
                break;
        }
    }
}
