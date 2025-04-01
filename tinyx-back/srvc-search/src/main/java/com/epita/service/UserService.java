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
        return Response.ok().build();
    }
}
