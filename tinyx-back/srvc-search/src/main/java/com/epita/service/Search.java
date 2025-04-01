package com.epita.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/search")
@ApplicationScoped
public class Search {

    @GET
    @Path("/user")
    public Response getUser() {
        return Response.ok("this is a user").build();
    }
}
