package com.epita.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostContract {

    public UUID id;
    public UUID author;

    public List<UUID> likes;
    public List<UUID> retweets;

    public String content;

    public Date updated_at;
    public Date created_at;
}
