package com.epita.controller;

import com.epita.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/user")
@ApplicationScoped
public class UserController {

    @Inject
    UserService userService;


    //-------------------Like-------------------

    /// This endpoint returns the list of all likes by all users.
    @GET
    @Path("/likes/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikes() {
        return userService.getLikes();
    }

    /// This endpoint returns the list of all likes by the user with the given id.
    @GET
    @Path("/likes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLikes(@HeaderParam("X-user-id") UUID userId) {
        return userService.getUserLikes(userId);
    }

    /// This endpoint returns the list of all likes by the user with the given id.
    @GET
    @Path("/likes/post")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLikesByPost(@HeaderParam("X-post-id") UUID postId) {
        return userService.getUserLikesByPost(postId);
    }

    /// This endpoint adds a new like by the user
    /// with the given userId to the user with the given likedPostId.
    @POST
    @Path("/like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLike(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-post-id") UUID likedPostId) {
        return userService.addLike(userId, likedPostId);
    }

    /// This endpoint deletes the like from the user
    /// with the given userId to the post with the given postId.
    @DELETE
    @Path("/unlike")
    @Produces(MediaType.APPLICATION_JSON)
    public Response unLike(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-post-id") UUID postId) {
        return userService.deleteLike(userId, postId);
    }



    //-------------------Follow-------------------

    /// This endpoint returns the list of all follow relations between all users.
    @GET
    @Path("/follows/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollows() {
        return userService.getFollows();
    }

    /// This endpoint returns the list of all the users
    /// followed by the user with the given userId.
    @GET
    @Path("/follows")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFollows(@HeaderParam("X-user-id") UUID userId) {
        return userService.getUserFollows(userId);
    }

    /// This endpoint returns the list of all the users
    /// following the user with the given userId.
    @GET
    @Path("/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@HeaderParam("X-user-id") UUID userId) {
        return userService.getFollowers(userId);
    }

    /// This endpoint adds a new follow relation from the user
    /// with the given userId to the user with the given followedUserId.
    @POST
    @Path("/follow")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFollow(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-followed-id") UUID followedUserId) {
        return userService.addFollow(userId, followedUserId);
    }

    /// This endpoint deletes the follow relation from the user
    /// with the given userId to the user with the given followId.
    @DELETE
    @Path("/unfollow")
    @Produces(MediaType.APPLICATION_JSON)
    public Response unFollow(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-follow-id") UUID followedUserId) {
        return userService.deleteFollow(userId, followedUserId);
    }



    //-------------------Block-------------------

    /// This endpoint returns the list of all block relations between all users.
    @GET
    @Path("/blocks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlocks() {
        return userService.getBlocks();
    }

    /// This endpoint returns the list of all the users blocked by the user with the given userId.
    @GET
    @Path("/blockers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserBlockers(@HeaderParam("X-user-id") UUID userId) {
        return userService.getUserBlockers(userId);
    }

    /// This endpoint returns the list of all the users who had blocked the user with the given userId.
    @GET
    @Path("/blocked")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserBlocked(@HeaderParam("X-user-id") UUID userId) {
        return userService.getUserBlocked(userId);
    }

    /// This endpoint adds a new block relation from the user
    /// with the given userId to the user with the given blockedUserId.
    @POST
    @Path("/block")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBlock(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-blocked-id") UUID blockedUserId) {
        return userService.addBlock(userId, blockedUserId);
    }

    /// This endpoint deletes the block relation from the user
    /// with the given userId and the user with the given blockedUserId.
    @DELETE
    @Path("/unblock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response unBlock(@HeaderParam("X-user-id") UUID userId, @HeaderParam("X-blocked-id") UUID blockedUserId) {
        return userService.deleteBlock(userId, blockedUserId);
    }
}
