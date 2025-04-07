package com.epita.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TimelineEntry {
    private UUID postId;
    private Date timestamp;
}
