package com.epita.repository;

import com.epita.repository.entity.Timeline;
import com.epita.repository.entity.TimelineEntry;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

import static io.quarkus.mongodb.panache.PanacheMongoEntityBase.findByIdOptional;

@ApplicationScoped
public class TimelineRepository implements PanacheMongoRepositoryBase<Timeline, UUID> {

    public Optional<Timeline> findByUserId(UUID userId) {
        return findByIdOptional(userId);
    }

    public void addOrUpdateTimelineEntry(Timeline timeline, UUID postId, Date timestamp) {
        if (timestamp == null) {
            timestamp = new Date();
        }

        timeline.getEntries().add(new TimelineEntry(postId, timestamp));
        timeline.getEntries().sort(
                Comparator.comparing(TimelineEntry::getTimestamp, Comparator.nullsLast(Comparator.naturalOrder()))
        );

        persistOrUpdate(timeline);
    }

    public void removeTimelineEntry(Timeline timeline, UUID postId) {
        timeline.getEntries().removeIf(entry -> entry.getPostId().equals(postId));
        persistOrUpdate(timeline);
    }

    public void saveTimeline(Timeline timeline) {
        persist(timeline);
    }
}