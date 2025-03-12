package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.sql.Timestamp;
import java.util.UUID;

@MongoEntity(collection = "Likes", database = "tinyX")
public class Like {

    /*
     * This class represents a like given by a user to a post.
     *
     * user: the user who liked the post.
     * postId: the id of the post that was liked.
     * created: the timestamp of when the like was created.
     */

    private UUID id;
    public User user;
    public UUID postId;
    public Timestamp created;


    public Like(User user, final UUID postId) {
        this.user = user;
        this.postId = postId;
        this.created = new Timestamp(System.currentTimeMillis());
        this.id = UUID.randomUUID();
    }

    public Like(User user, UUID postId, Timestamp created) {
        this.user = user;
        this.postId = postId;
        this.created = created;
        this.id = UUID.randomUUID();
    }
}
