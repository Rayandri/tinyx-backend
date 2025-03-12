package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

@MongoEntity(collection = "FollowRelations", database = "tinyX")
public class FollowRelation {
    /*
     * This class represents a blocker-blocked relation between 2 Users.
     *
     * blocker: The User who initiated the block relation.
     * blocked: The User who is blocked by the blocker.
     */

    @BsonId
    private UUID id;
    public User follower;
    public User followed;

    public FollowRelation(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
        this.id = UUID.randomUUID();
    }
}
