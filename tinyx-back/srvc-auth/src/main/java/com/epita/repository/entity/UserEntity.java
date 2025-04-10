package com.epita.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "Users")
public class UserEntity {

    @BsonId
    private UUID id = UUID.randomUUID();
    public String username;
    public String password_hash;
    public Date created_at;
    public Date updated_at;

}
