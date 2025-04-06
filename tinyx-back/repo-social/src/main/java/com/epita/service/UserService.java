package com.epita.service;

import com.epita.repository.BlockRepository;
import com.epita.repository.FollowRepository;
import com.epita.repository.LikeRepository;
import com.epita.repository.UserRepository;
import com.epita.repository.entity.BlockRelation;
import com.epita.repository.entity.FollowRelation;
import com.epita.repository.entity.Like;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    LikeRepository likeRepository;
    @Inject
    FollowRepository followRepository;
    @Inject
    BlockRepository blockRepository;




    //-------------------User-------------------

    public Response getUsers() {
        return null;
    }

    public Response getUser() {
        return null;
    }

    public Response addUser(String username) {
        return null;
    }

    public Response deleteUser(UUID id) {
        return null;
    }




    //-------------------Like-------------------

    public Response getLikes() {
        List<Like> likes = likeRepository.listAllLikes();
        if (likes == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(likes).build();
    }

    public Response getUserLikes(UUID userId) {
        return Response.ok(likeRepository.findLikesByUserId(userId)).build();
    }

    public Response getUserLikesByPost(UUID postId) {
        return Response.ok(likeRepository.findLikesByPostId(postId)).build();
    }

    public Response addLike(UUID userId, UUID postId) {
        likeRepository.addLike(userId, postId);
        return Response.ok().build();
    }

    public Response deleteLike(UUID userId, UUID likeId) {
        try {
            likeRepository.removeLike(userId, likeId);
            return Response.ok().build();
        }
        catch (NotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }




    //-------------------Follow-------------------

    public Response getFollows() {
        return Response.ok(followRepository.listAllFollows()).build();
    }

    public Response getUserFollows(UUID userId) {
        return Response.ok(followRepository.getAllFollowed(userId)).build();
    }

    public Response getFollowers(UUID userId) {
        return Response.ok( followRepository.getAllFollowers(userId)).build();
    }

    public Response addFollow(UUID userId, UUID followedUserId) {
        try {
            followRepository.addFollow(new FollowRelation(userId, followedUserId));
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    public Response deleteFollow(UUID userId, UUID followId) {
        try{
            followRepository.removeFollow(userId, followId);
        } catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }




    //-------------------Block-------------------

    public Response getBlocks() {
        return Response.ok(blockRepository.listAllBlocks()).build();
    }

    public Response getUserBlocked(UUID userId) {
        return Response.ok(blockRepository.getAllBlocked(userId)).build();
    }

    public Response getUserBlockers(UUID userId) {
        return Response.ok(blockRepository.getAllBlockers(userId)).build();
    }

    public Response addBlock(UUID userId, UUID blockedUserId) {
        try{
            blockRepository.addBlock(new BlockRelation(userId, blockedUserId));
        } catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    public Response deleteBlock(UUID userId, UUID blockId) {
        try {
            blockRepository.removeBlock(userId, blockId);
        } catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}