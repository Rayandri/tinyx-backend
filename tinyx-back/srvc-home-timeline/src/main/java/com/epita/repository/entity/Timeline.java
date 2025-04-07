package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@MongoEntity(collection = "home_timelines")
public class Timeline {
    public UUID id; // identique à userId
    public UUID userId;
    public List<TimelineEntry> entries = new ArrayList<>();
}