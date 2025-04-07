package com.epita.repository;

import com.epita.repository.entity.User;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepositoryBase<User, UUID> {

    public User findUserById(UUID id) {
        return findById(id);
    }

    public User findUserByUsername(String username) {
        return find("username", username).firstResult();
    }

    public List<User> listAllUsers() {
        return listAll();
    }

    public void addUser(User user) {
        persist(user);
    }
}
