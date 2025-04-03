package com.epita.controller;

import com.epita.repository.entity.Post;
import com.epita.service.PostService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostController {

    @Inject
    PostService postService;

    @POST
    @Path("/create/post")
    public Response createPost(Post post) {
        postService.addPost(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @GET
    @Path("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
