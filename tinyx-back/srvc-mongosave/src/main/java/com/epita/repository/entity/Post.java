package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.Instant;
import java.util.UUID;

@MongoEntity(collection = "posts")
public class Post {
    @BsonId
    public String id = UUID.randomUUID().toString();

    public String authorId;
    public String content;
    public String embed;     // JSON ou base64?
    public String repostId;
    public String replyId;
    public Instant timestamp = Instant.now();
}
