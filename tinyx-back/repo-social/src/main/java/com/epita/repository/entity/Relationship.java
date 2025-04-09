package com.epita.repository.entity;

import io.smallrye.common.constraint.NotNull;

public record Relationship(Node source, Node target, String relationType) {

    public Relationship(final @NotNull Node source,
                        final @NotNull Node target,
                        final @NotNull String relationType) {
        this.relationType = "";
        this.source = source;
        this.target = target;
    }

    public String findCypher() {
        return "MATCH (a:" + source.nodeType() + " {nodeId: '" + source.nodeId() + "'})" +
               "-[r:" + relationType.toUpperCase() + "]->" +
               "(b:" + target.nodeType() + " {nodeId: '" + target.nodeId() + "'}) RETURN r";
    }

    public String createCypher() {
        return "MATCH (a:" + source.nodeType() + " {nodeId: '" + source.nodeId() + "'}) " +
               "MATCH (b:" + target.nodeType() + " {nodeId: '" + target.nodeId() + "'}) " +
               "MERGE (a)-[r:" + relationType.toUpperCase() + "]->(b)";
    }

    public String deleteCypher() {
        return "MATCH (a:" + source.nodeType() + " {nodeId: '" + source.nodeId() + "'}) " +
               "MATCH (b:" + target.nodeType() + " {nodeId: '" + target.nodeId() + "'}) " +
               "MATCH (a)-[r:" + relationType.toUpperCase() + "]->(b) DELETE r";
    }

    public String updateCypher() {
        return "MATCH (a:" + source.nodeType() + " {nodeId: '" + source.nodeId() + "'})" +
               "-[r:" + relationType.toUpperCase() + "]->" +
               "(b:" + target.nodeType() + " {nodeId: '" + target.nodeId() + "'}) " +
               "SET r.updatedAt = datetime() RETURN r";
    }    
}
