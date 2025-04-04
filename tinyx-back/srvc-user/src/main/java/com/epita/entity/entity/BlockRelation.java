package com.epita.entity.entity;

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
    public UUID blockerId;
    public UUID blockedId;

    public BlockRelation(UUID blocker, UUID blocked) {
        this.blockerId = blocker;
        this.blockedId = blocked;
        this.id = UUID.randomUUID();
    }
}
