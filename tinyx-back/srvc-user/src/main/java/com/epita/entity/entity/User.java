package com.epita.entity.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.sql.Timestamp;
import java.util.UUID;

@MongoEntity(collection = "Users", database = "tinyX")
public class User {
    /*
     * This class represents a User.
     *
     * id: The unique identifier of the User.
     * username: The username of the User.
     */

    @BsonId
    private final UUID id;
    public String username;
    public Timestamp created;


    public User(String name) {
        this.username = name;
        this.id = UUID.randomUUID();
    }
}
