package com.epita.repository.entity;

import io.smallrye.common.constraint.NotNull;

public record Relationship(Node source, Node target, String relationType) {

    public Relationship(final @NotNull Node source,
                        final @NotNull Node target,
                        final @NotNull String relationType) {
        this.relationType = relationType;
        this.source = source;
        this.target = target;
    }

    public String findCypher() {
        return "MATCH (a:" + source.nodeType().toString() + " {nodeId: '" + source.nodeId().toString() + "'})" +
               "-[r:" + relationType.toUpperCase() + "]->" +
               "(b:" + target.nodeType().toString()+ " {nodeId: '" + target.nodeId().toString() + "'}) RETURN r";
    }

    public String createCypher() {
        return "MATCH (a:" + source.nodeType().toString() + " {nodeId: '" + source.nodeId().toString() + "'}) " +
               "MATCH (b:" + target.nodeType().toString() + " {nodeId: '" + target.nodeId().toString() + "'}) " +
               "MERGE (a)-[r:" + relationType.toUpperCase() + "]->(b)";
    }

    public String deleteCypher() {
        return "MATCH (a:" + source.nodeType().toString() + " {nodeId: '" + source.nodeId().toString() + "'}) " +
               "MATCH (b:" + target.nodeType().toString() + " {nodeId: '" + target.nodeId().toString() + "'}) " +
               "MATCH (a)-[r:" + relationType.toUpperCase() + "]->(b) DELETE r";
    }

    public String updateCypher() {
        return "MATCH (a:" + source.nodeType().toString() + " {nodeId: '" + source.nodeId().toString() + "'})" +
               "-[r:" + relationType.toUpperCase() + "]->" +
               "(b:" + target.nodeType().toString() + " {nodeId: '" + target.nodeId().toString() + "'}) " +
               "SET r.updatedAt = datetime() RETURN r";
    }    
}
