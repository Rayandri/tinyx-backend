package com.epita.service;

import com.epita.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/search/user")
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/{name}")
    public Response getUser(@PathParam("name") String name) {
        /*
         * search in mongodb for the user that match the "name" field
         * here name refer to a regex that have to be use to look for users
         */


        return Response.ok().build();
    }
}
