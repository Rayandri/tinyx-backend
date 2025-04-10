package com.epita.repository;

import com.epita.repository.entity.Relationship;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.neo4j.driver.*;


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
}
