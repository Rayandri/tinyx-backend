package com.epita.controller;

import com.epita.domain.entity.PostEntity;
import com.epita.domain.entity.UserEntity;
import com.epita.domain.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/api/search/user")
@ApplicationScoped
public class UserController {

    private UserService userService;

    @GET
    @Path("/{regex}")
    public Response getUser(@PathParam("regex") String name) {
        /*
         * search in mongodb for the user that match the "name" field
         * here name refer to a regex that have to be use to look for users
         */

        List<UserEntity> users = userService.aggregateUsers(name);

        if (users.isEmpty()) {
            return Response.status(404).build();
        }

        return Response.ok(users).build();
    }

    @GET
    @Path("/posts/{id}")
    public Response getUserPosts(@PathParam("id") UUID id) {
        /*
        * search in mongodb all the post made by this user
        * */

        List<PostEntity> posts = userService.aggregatePosts(id);

        if (posts.isEmpty()) {
            return Response.status(404).build();
        }

        return Response.ok(posts).build();
    }
}
