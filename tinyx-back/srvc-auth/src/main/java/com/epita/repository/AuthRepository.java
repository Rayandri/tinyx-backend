package com.epita.repository;

import com.epita.controller.contracts.UserContract;
import com.epita.repository.entity.UserEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class AuthRepository implements PanacheMongoRepositoryBase<UserEntity, UUID> {



    public void createUser(UserEntity user) {
        persist(user);
    }

    public void updateUser(UserEntity user) {
        update(user);
    }

    public UserEntity getUser(UUID id) {
        return findById(id);
    }

    public UserEntity getUserByUsernameAndHashPassword(String username, String password_hash) {
        return find("{'username': ?1, 'password_hash': ?2}", username, password_hash).firstResult();
    }

    public void deleteUser(UUID id) {
       deleteById(id);
    }

    public boolean userExists(String username) {
        return find("username", username).firstResult() != null;
    }

    public List<UserEntity> getAllUsers() {
        return findAll().list();
    }
}
