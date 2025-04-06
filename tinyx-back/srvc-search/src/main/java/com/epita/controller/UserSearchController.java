package com.epita.controller;


import com.epita.service.UserSearchService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/search/user")
@ApplicationScoped
public class UserSearchController {

    @Inject
    UserSearchService userService;



    @GET
    @Path("/posts/{id}")
    public Response getUserPosts(@PathParam("id") UUID id) {
        /*
        * search in mongodb all the post made by this user
        * */
        return userService.getUserPosts(id);
    }
    @GET
    @Path("/name/{name}")
    public Response getUser(@PathParam("name") String name) {
        /*
         * search in mongodb for the user that match the "name" field
         * here name refer to a regex that have to be use to look for users
         */
        return userService.getUser(name);
    }
}
