package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

@MongoEntity(collection = "users")
public class User {
    @BsonId
    public String id = UUID.randomUUID().toString(); //@PrePersist stv?

    public String name;
    public String bio;
    public String pfp; //Url ou base64?
}
