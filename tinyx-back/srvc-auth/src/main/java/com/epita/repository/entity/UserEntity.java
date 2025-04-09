package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@MongoEntity(collection = "Users", database = "tinyX") //??
public class UserEntity {

    @BsonId
    private final UUID id;
    public String username;
    public String password;
    public Timestamp created;
    public Timestamp updated;


    public UserEntity(String name,String password, Timestamp created, Timestamp updated) {
        this.username = name;
        this.id = UUID.randomUUID();
        this.password = password;
        this.updated = updated;
        this.created = created;
    }
}
