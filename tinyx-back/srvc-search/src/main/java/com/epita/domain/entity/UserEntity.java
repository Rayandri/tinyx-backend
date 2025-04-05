package com.epita.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MongoEntity(collection = "users")
public class UserEntity extends PanacheMongoEntity {
    @Getter
    @Setter
    UUID id = UUID.randomUUID();

    @Getter
    @Setter
    String name;

}
