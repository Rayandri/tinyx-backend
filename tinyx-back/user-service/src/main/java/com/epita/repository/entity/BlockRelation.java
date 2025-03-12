package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

@MongoEntity(collection = "BlockRelations", database = "tinyX")
public class BlockRelation {
    /*
    * This class represents a blocker-blocked relation between 2 Users.
    *
    * blocker: The User who initiated the block relation.
    * blocked: The User who is blocked by the blocker.
    */

    @BsonId
    private UUID id;
    public User blocker;
    public User blocked;

    public BlockRelation(User blocker, User blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.id = UUID.randomUUID();
    }
}
