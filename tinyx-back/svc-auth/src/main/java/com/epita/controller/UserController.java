package com.epita.controller;

import com.epita.controller.contracts.CreateUserRequest;
import com.epita.controller.contracts.LoginRequest;
import com.epita.controller.contracts.UpdatePasswordRequest;
import com.epita.repository.entity.UserContract;
import com.epita.service.AuthService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;
@Path("/api/auth")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    AuthService authService;

    @POST
    @Path("/create")
    public Response createUser(CreateUserRequest request) {
        if (request.username == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.createUser(request.username, request.password);
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        if (request.username == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.login(request.username, request.password);
    }

    @POST
    @Path("/update/password")
    public Response changePassword(@HeaderParam("X-user-id") UUID id, UpdatePasswordRequest request) {
        if (id == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        System.out.println("id: " + id);
        return authService.updatePassword(id, request.password);
    }

    @POST
    @Path("/delete")
    public Response deleteAccount(@HeaderParam("X-user-id") UUID id) {
        if (id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.deleteUser(id);
    }
}
