package com.epita.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path("/api/search/test")
@ApplicationScoped
public class Hello
{
    @GET
    @Path("/hello")
    public Response searchCheck(){
        return Response.ok("Hello World!").build();
    }
}
