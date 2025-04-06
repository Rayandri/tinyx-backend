package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MongoEntity(collection = "PostContents", database = "tinyX")
public class PostContentEntity {

    /**
     * This class holds the content of a post.
     * This was done apart to make ElasticSearch implementation easier for you <3.
     * We were not sure whether to include the post id in the fields. We figured you'd decide on that.
     */

    @BsonId
    private UUID id;
    private String text;
    private String media;
    private UUID repost;

    public PostContentEntity(String text, String media, UUID repost) throws IllegalArgumentException{

        boolean allThere = text != null && media != null && repost != null;
        boolean noneThere = text == null && media == null && repost == null;
        if (allThere || noneThere) {
            throw new IllegalArgumentException("There must be at least one and at most two of the fields: text, media, repost");
        }

        this.id  = UUID.randomUUID();
        this.text = text;
        this.media = media;
        this.repost = repost;
    }
}