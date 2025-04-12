package com.epita.controller;

import com.epita.controller.contracts.CreateUserRequest;
import com.epita.controller.contracts.LoginRequest;
import com.epita.controller.contracts.UpdatePasswordRequest;
import com.epita.service.AuthService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;
@Path("/api")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    AuthService authService;

    @GET
    @Path("/user/all")
    public Response getAllUsers() {
        return authService.getAllUsers();
    }

    @POST
    @Path("/auth/create")
    public Response createUser(CreateUserRequest request) {
        if (request.username == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.createUser(request.username, request.password);
    }

    @POST
    @Path("/auth/login")
    public Response login(LoginRequest request) {
        if (request.username == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.login(request.username, request.password);
    }

    @POST
    @Path("/auth/update/password")
    public Response changePassword(@HeaderParam("X-user-id") UUID id, UpdatePasswordRequest request) {
        if (id == null || request.password == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        System.out.println("id: " + id);
        return authService.updatePassword(id, request.password);
    }

    @POST
    @Path("/auth/delete")
    public Response deleteAccount(@HeaderParam("X-user-id") UUID id) {
        if (id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return authService.deleteUser(id);
    }
}
