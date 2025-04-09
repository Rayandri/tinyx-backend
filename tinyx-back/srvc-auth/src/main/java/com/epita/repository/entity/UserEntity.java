package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;



import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MongoEntity(collection = "Users")
public class UserEntity {

    @BsonId
    private final UUID id;
    public String username;
    public String password_hash;
    public Date created_at;
    public Date updated_at;


    public UserEntity(String name,String password_hash, Date created_at, Date updated_at) {
        this.username = name;
        this.id = UUID.randomUUID();
        this.password_hash = password_hash;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }
}
