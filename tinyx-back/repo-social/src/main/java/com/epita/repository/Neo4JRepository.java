package com.epita.repository;

import com.epita.controller.contract.FollowRelation;
import com.epita.controller.contract.LikeRelation;
import com.epita.repository.entity.Relationship;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.neo4j.driver.*;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class Neo4JRepository {

    @Inject
    Driver driver;

    public void runQuery(String cypherQuery) {
        try (Session session = driver.session()) {
            session.run(cypherQuery);
        }
    }

    public void createSimpleRelationship(Relationship relationship) {
        runQuery(relationship.createCypher());
    }

    public void deleteSimpleRelationship(Relationship relationship) {
        runQuery(relationship.deleteCypher());
    }


    public List<String> getAllRelation(UUID userId, String relationType) {
        String query = """
            MATCH (u:User {nodeId: $userId})-[r:%s]->(f:User)
            RETURN f.nodeId AS followerId
        """.formatted(relationType);

        try (Session session = driver.session()) {
            List<String> followers = session.executeRead(tx ->
                    tx.run(query, Values.parameters("userId", userId))
                            .list(record -> record.get("followerId").asString())
            );
            return followers;
        }
    }

    public List<String> getAllRelationReverse(UUID userId, String relationType) {
        String query = """
            MATCH (f:User)-[:%s]->(u:User {nodeId: $userId})
            RETURN f.nodeId AS followerId
        """.formatted(relationType);

        try (Session session = driver.session()) {
            List<String> followers = session.executeRead(tx ->
                    tx.run(query, Values.parameters("userId", userId))
                            .list(record -> record.get("followerId").asString())
            );
            return followers;
        }
    }

    public List<FollowRelation> listAllRelation(String relationType) {
        String query = """
            MATCH (a:User)-[:%s]->(b:User)
            RETURN a.nodeId AS followerId, b.nodeId AS followedId
        """.formatted(relationType);

        try (Session session = driver.session()) {
            return session.executeRead(tx ->
                    tx.run(query).list(record ->
                            new FollowRelation(
                                    record.get("followerId").asString(),
                                    record.get("followedId").asString()
                            )
                    )
            );
        }
    }

    public List<String> getAllFollowers(UUID userId) {
        return getAllRelationReverse(userId, "FOLLOWS");
    }

    public List<String> getAllFollowed(UUID userId) {
        return getAllRelation(userId, "FOLLOWS");
    }

    public List<String> getUserLikes(UUID userId) {
        String query = """
            MATCH (u:User {nodeId: $userId})-[r:LIKES]->(p:Post)
            RETURN p.nodeId AS postId
        """;

        try (Session session = driver.session()) {
            return session.executeRead(tx ->
                    tx.run(query, Values.parameters("userId", userId))
                            .list(record -> record.get("postId").asString())
            );
        }
    }

    public List<String> getUserLikesByPost(UUID postId) {
        String query = """
            MATCH (u:User)-[r:LIKES]->(p:Post {nodeId: $postId})
            RETURN u.nodeId AS userId
        """;

        try (Session session = driver.session()) {
            return session.executeRead(tx ->
                    tx.run(query, Values.parameters("postId", postId))
                            .list(record -> record.get("userId").asString())
            );
        }
    }

    public List<LikeRelation> listAllRelationLikes() {
        String query = """
            MATCH (a:User)-[:LIKES]->(b:Post)
            RETURN a.nodeId AS followerId, b.nodeId AS followedId
        """;

        try (Session session = driver.session()) {
            return session.executeRead(tx ->
                    tx.run(query).list(record ->
                            new LikeRelation(
                                    record.get("followerId").asString(),
                                    record.get("followedId").asString()
                            )
                    )
            );
        }
    }


}
