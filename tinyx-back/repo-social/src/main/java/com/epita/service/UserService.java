package com.epita.service;

import com.epita.controller.contract.LikeRelation;
import com.epita.controller.contract.PostEntityPublish;
import com.epita.controller.contract.UserEntityPublish;
import com.epita.repository.Neo4JRepository;
import com.epita.controller.PostPublisher;
import com.epita.controller.UserPublisher;
import com.epita.repository.entity.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Inject
    Neo4JRepository neo4JRepository;

    @Inject
    PostPublisher postPublisher;

    @Inject
    UserPublisher userPublisher;

    //-------------------Like-------------------

    public Response getLikes() {
        List<LikeRelation> likes = neo4JRepository.listAllRelationLikes();
        if (likes == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(likes).build();
    }

    public Response getUserLikes(UUID userId) {
        return Response.ok(neo4JRepository.getUserLikes(userId)).build();
    }

    public Response getUserLikesByPost(UUID postId) {
        return Response.ok(neo4JRepository.getUserLikesByPost(postId)).build();
    }

    public Response addLike(UUID userId, UUID postId) {
        var userNode = new Node(Node.NodeType.USER, userId.toString());
        var postNode = new Node(Node.NodeType.POST, postId.toString());
        var rel = new Relationship(userNode, postNode, "LIKES");

        neo4JRepository.runQuery(userNode.createCypher());
        neo4JRepository.runQuery(postNode.createCypher());
        neo4JRepository.runQuery(rel.createCypher());

        var likeRelation = new PostEntityPublish(userId, PostEntityPublish.PostAction.LIKED, postId);
        postPublisher.publish(likeRelation);

        return Response.ok().build();
    }

    public Response deleteLike(UUID userId, UUID likeId) {
        var userNode = new Node(Node.NodeType.USER, userId.toString());
        var postNode = new Node(Node.NodeType.POST, likeId.toString());
        var rel = new Relationship(userNode, postNode, "LIKES");
        neo4JRepository.deleteSimpleRelationship(rel);

        var likeRelation = new PostEntityPublish(userId, PostEntityPublish.PostAction.UNLIKED, likeId);
        postPublisher.publish(likeRelation);

        return Response.ok().build();
    }


    //-------------------Follow-------------------

    public Response getFollows() {
        return Response.ok(neo4JRepository.listAllRelation("FOLLOWS")).build();
    }

    public Response getUserFollows(UUID userId) {
        return Response.ok(neo4JRepository.getAllFollowed(userId)).build();
    }

    public Response getFollowers(UUID userId) {
        return Response.ok(neo4JRepository.getAllFollowers(userId)).build();
    }

    public Response addFollow(UUID userId, UUID followedUserId) {
        LOGGER.infof("[USER] Follow : Trying to follow user : %s", followedUserId);

        var follower = new Node(Node.NodeType.USER, userId.toString());
        var followed = new Node(Node.NodeType.USER, followedUserId.toString());
        var rel = new Relationship(follower, followed, "FOLLOWS");
        neo4JRepository.runQuery(follower.createCypher());
        neo4JRepository.runQuery(followed.createCypher());
        neo4JRepository.runQuery(rel.createCypher());

        var likeRelation = new UserEntityPublish(userId, followedUserId, UserEntityPublish.UserAction.FOLLOW);
        userPublisher.publish(likeRelation);

        LOGGER.infof("[USER] Follow : User : %s followed user : %s", userId, followedUserId);

        return Response.ok().build();
    }

    public Response deleteFollow(UUID userId, UUID followId) {
        var follower = new Node(Node.NodeType.USER, userId.toString());
        var followed = new Node(Node.NodeType.USER, followId.toString());
        var rel = new Relationship(follower, followed, "FOLLOWS");
        neo4JRepository.runQuery(rel.deleteCypher());

        var likeRelation = new UserEntityPublish(userId, followId, UserEntityPublish.UserAction.UNFOLLOW);
        userPublisher.publish(likeRelation);

        return Response.ok().build();

    }

    //-------------------Block-------------------

    public Response getBlocks() {
        return Response.ok(neo4JRepository.listAllRelation("BLOCKS")).build();
    }

    public Response getUserBlocked(UUID userId) {
        return Response.ok(neo4JRepository.getAllRelationReverse(userId, "BLOCKS")).build();
    }

    public Response getUserBlockers(UUID userId) {
        return Response.ok(neo4JRepository.getAllRelation(userId, "BLOCKS")).build();
    }

    public Response addBlock(UUID userId, UUID blockedUserId) {
        var blocker = new Node(Node.NodeType.USER, userId.toString());
        var blocked = new Node(Node.NodeType.USER, blockedUserId.toString());
        var rel = new Relationship(blocker, blocked, "BLOCKS");
        neo4JRepository.runQuery(blocked.createCypher());
        neo4JRepository.runQuery(blocker.createCypher());
        neo4JRepository.runQuery(rel.createCypher());
        return Response.ok().build();
    }

    public Response deleteBlock(UUID userId, UUID blockId) {
        var blocker = new Node(Node.NodeType.USER, userId.toString());
        var blocked = new Node(Node.NodeType.USER, blockId.toString());
        var rel = new Relationship(blocker, blocked, "BLOCKS");
        neo4JRepository.runQuery(rel.deleteCypher());
        return Response.ok().build();
    }
}
