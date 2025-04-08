package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@MongoEntity(collection = "Posts", database = "tinyX")
public class PostEntity {
    /**
     * This class holds all the information about a post.
     * Note that the content of the post is stored in a separate collection to ease logistics.
     */

    @BsonId
    private final UUID id;
    private final UUID authorId;
    @Setter private UUID content;
    private final Date createdAt;
    private final UUID replyTo;

    public PostEntity(UUID authorId, UUID content, UUID replyTo) {
        this.id = UUID.randomUUID();
        this.authorId = authorId;
        this.content = content;
        this.createdAt = Date.from(new Timestamp(System.currentTimeMillis()).toInstant());
        this.replyTo = replyTo;
    }
}