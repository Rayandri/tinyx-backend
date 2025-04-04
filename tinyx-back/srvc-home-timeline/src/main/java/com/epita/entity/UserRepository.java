package com.epita.entity;

import com.epita.controller.contracts.UserContract;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class UserRepository {

    Map<UUID, UserContract> users = new HashMap<UUID, UserContract>();

    public UserContract getUser(UUID id) {
        return users.get(id);
    }
}