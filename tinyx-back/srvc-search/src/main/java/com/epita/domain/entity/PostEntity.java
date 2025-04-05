package com.epita.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.UUID;

@MongoEntity(collection = "posts")
public class PostEntity {
    /*
    * what is a post in the mongodb data base
    * this class is suposse to be a java version of a post
    * */

    @Getter
    @Setter
    private UUID userId;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String image = null;
}
