package com.epita.controller;

import com.epita.controller.contracts.PostContentContract;
import com.epita.service.PostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api")
@ApplicationScoped
public class PostController {

    @Inject
    PostService postService;

    /// This endpoint returns the list of all posts.
    @GET
    @Path("/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts() {
        return postService.getPosts();
    }

    /// This endpoint returns the list of all posts from a user.
    @GET
    @Path("/posts/user/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPosts(@QueryParam("id") UUID id) {
        return postService.getPostsFromUser(id);
    }

    /// This endpoint returns the post with the given UUID.
    @GET
    @Path("/post/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPost(@QueryParam("id") UUID id) {
        return postService.getPostFromId(id);
    }

    /// This endpoint returns the reply to the post with the given UUID, if any.
    @GET
    @Path("post/reply")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReply(@QueryParam("id") UUID id) {
        return postService.getPostReply(id);
    }

    /// This endpoint adds a new post with the given content authored by the user with the given UUID.
    @GET
    @Path("/post/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPost(@HeaderParam("X-user-id") UUID userId, PostContentContract content) {
        return postService.addPost(userId, content);
    }

    /// This endpoint deletes the post with the given UUID.
    @GET
    @Path("/post/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPost(@HeaderParam("X-user-id") UUID userId, UUID postId) {
        return postService.deletePost(userId, postId);
    }
}
