package com.epita.service;

import com.epita.repository.BlockRepository;
import com.epita.repository.FollowRepository;
import com.epita.repository.LikeRepository;
import com.epita.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

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
        return null;
    }

    public Response getUserLikes(UUID userId) {
        return null;
    }

    public Response addLike(UUID userId, UUID likedUserId) {
        return null;
    }

    public Response deleteLike(UUID userId, UUID likeId) {
        return null;
    }




    //-------------------Follow-------------------

    public Response getFollows() {
        return null;
    }

    public Response getUserFollows(UUID userId) {
        return null;
    }

    public Response getFollowers(UUID userId) {
        return null;
    }

    public Response addFollow(UUID userId, UUID followedUserId) {
        return null;
    }

    public Response deleteFollow(UUID userId, UUID followId) {
        return null;
    }




    //-------------------Block-------------------

    public Response getBlocks() {
        return null;
    }

    public Response getUserBlocks(UUID userId) {
        return null;
    }

    public Response addBlock(UUID userId, UUID blockedUserId) {
        return null;
    }

    public Response deleteBlock(UUID userId, UUID blockId) {
        return null;
    }
}