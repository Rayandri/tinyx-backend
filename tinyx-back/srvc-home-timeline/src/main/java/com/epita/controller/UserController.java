package com.epita.controller;

import com.epita.controller.contracts.CreateUserRequest;
import com.epita.controller.contracts.LoginRequest;
import com.epita.controller.contracts.UpdatePasswordRequest;
import com.epita.service.HomeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;
@Path("/api/timeline")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    HomeService homeService;

    @GET
    @Path("/home")
    public Response getTimeline(@HeaderParam("X-user-id") UUID id) {
        return homeService.getTimeline(id);
    }

    @GET
    @Path("/home/update")
    public Response getUpdateOfTheTimeLine(@HeaderParam("X-user-id") UUID id) {

        return homeService.getUpdateOfTheTimeLine(id);

    }
}
