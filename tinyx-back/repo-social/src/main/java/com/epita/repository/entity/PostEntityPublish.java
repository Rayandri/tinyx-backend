package com.epita.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEntityPublish {

    public enum PostAction {
        CREATE,
        DELETE,
        LIKED,
        UNLIKED,
    }

    public UUID id;
    public UUID author;

    public List<UUID> likes;
    public List<UUID> retweets;

    public String content;

    public Date updated_at;
    public Date created_at;

    public PostAction action;
    // For like / unlike
    public PostEntityPublish(UUID userId, PostAction action, UUID postId) {
        this.id = userId;
        this.author = postId;
        this.action = action;
    }
}
