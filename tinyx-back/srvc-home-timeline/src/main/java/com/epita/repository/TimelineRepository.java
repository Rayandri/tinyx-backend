package com.epita.repository;

import com.epita.repository.entity.Timeline;
import com.epita.repository.entity.TimelineEntry;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TimelineRepository implements PanacheMongoRepositoryBase<Timeline, UUID> {


    public Timeline findByUserId(UUID userId) {

        Optional<Timeline> optionalTimeline = findByIdOptional(userId);
        Timeline timeline = optionalTimeline.orElseGet(() -> {
            Timeline newTimeline = new Timeline();
            newTimeline.setUserId(userId);
            newTimeline.setId(userId);
            persist(newTimeline);
            return newTimeline;
        });


        return timeline;
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

}