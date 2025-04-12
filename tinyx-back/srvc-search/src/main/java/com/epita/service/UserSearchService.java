package com.epita.service;


import com.epita.repository.entity.PostEntity;
import com.epita.repository.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import com.epita.repository.UserSearchRepository;

import java.util.*;

@ApplicationScoped
public class UserSearchService {

    @Inject
    UserSearchRepository userSearchRepository;

    public Response getUser(String name) {
        List<UserEntity> users = userSearchRepository.aggregateUsers(name);

        if (users.isEmpty()) {
            return Response.status(404).build();
        }

        return Response.ok(users).build();
    }
}
