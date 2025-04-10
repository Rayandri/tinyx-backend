package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "Posts", database = "tinyX")
public class Post {
    /**
     * This class holds all the information about a post.
     * Note that the content of the post is stored in a separate collection to ease logistics.
     */

    @BsonId
    private  UUID id;
    private  UUID authorId;
    private  UUID content;
    private  Date createdAt;
    private  UUID replyTo;

    public Post(UUID authorId, UUID content, UUID replyTo) {
        this.id = UUID.randomUUID();
        this.authorId = authorId;
        this.content = content;
        this.createdAt = new Date();
        this.replyTo = replyTo;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\n Author: " + this.authorId + "\n Content: " + this.content + "\n Reply: " + this.replyTo;
    }
}
