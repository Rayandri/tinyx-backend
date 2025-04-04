package com.epita.entity;

import com.epita.controller.contracts.UserContract;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class AuthRepository {

    Map<UUID, UserContract> users = new HashMap<UUID, UserContract>();

    public void createUser(UserContract user) {
        users.put(user.id, user);
    }

    public void updateUser(UserContract user) {
        user.updated_at = new Date();
        users.put(user.id, user);
    }

    public UserContract getUser(UUID id) {
        return users.get(id);
    }

    public UserContract getUserByUsernameAndHashPassword(String username, String hash_password) {
        for (UserContract user : users.values()) {
            if (user.username.equals(username) && user.password_hash.equals(hash_password)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(UUID id) {
        users.remove(id);
    }

    public boolean userExists(String username) {
        for (UserContract user : users.values()) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }
}
