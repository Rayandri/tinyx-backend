package com.epita.service;

import com.epita.repository.BlockRepository;
import com.epita.repository.FollowRepository;
import com.epita.repository.LikeRepository;
import com.epita.repository.Neo4JRepository;
import com.epita.repository.UserRepository;
import com.epita.repository.entity.Like;
import com.epita.repository.entity.Node;
import com.epita.repository.entity.Relationship;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
    @Inject
    Neo4JRepository neo4JRepository;




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
        var userNode = new Node(Node.NodeType.USER, userId.toString());
        var postNode = new Node(Node.NodeType.POST, postId.toString());
        var rel = new Relationship(userNode, postNode, "LIKES");
        neo4JRepository.createSimpleRelationship(rel);
        return Response.ok().build();
    }

    public Response deleteLike(UUID userId, UUID likeId) {
        var userNode = new Node(Node.NodeType.USER, userId.toString());
        var postNode = new Node(Node.NodeType.POST, likeId.toString());
        var rel = new Relationship(userNode, postNode, "LIKES");
        neo4JRepository.deleteSimpleRelationship(rel);
        return Response.ok().build();
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
        var follower = new Node(Node.NodeType.USER, userId.toString());
        var followed = new Node(Node.NodeType.USER, followedUserId.toString());
        var rel = new Relationship(follower, followed, "FOLLOWS");
        neo4JRepository.createSimpleRelationship(rel);
        return Response.ok().build();
    }

    public Response deleteFollow(UUID userId, UUID followId) {
        var follower = new Node(Node.NodeType.USER, userId.toString());
        var followed = new Node(Node.NodeType.USER, followId.toString());
        var rel = new Relationship(follower, followed, "FOLLOWS");
        neo4JRepository.deleteSimpleRelationship(rel);
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
        var blocker = new Node(Node.NodeType.USER, userId.toString());
        var blocked = new Node(Node.NodeType.USER, blockedUserId.toString());
        var rel = new Relationship(blocker, blocked, "BLOCKS");
        neo4JRepository.createSimpleRelationship(rel);
        return Response.ok().build();
    }

    public Response deleteBlock(UUID userId, UUID blockId) {
        var blocker = new Node(Node.NodeType.USER, userId.toString());
        var blocked = new Node(Node.NodeType.USER, blockId.toString());
        var rel = new Relationship(blocker, blocked, "BLOCKS");
        neo4JRepository.deleteSimpleRelationship(rel);
        return Response.ok().build();
    }
}
