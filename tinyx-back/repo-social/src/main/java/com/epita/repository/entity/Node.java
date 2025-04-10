package com.epita.repository.entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.InternalServerErrorException;

public record Node(NodeType nodeType, String nodeId) {
    public enum NodeType {

        USER("User"),
        POST("Post");

        private final String typeStr;

        NodeType(final String typeStr) {
            this.typeStr = typeStr;
        }

        @Override
        public String toString() {
            return typeStr;
        }

        public static NodeType fromString(final @NotNull String str) {
            for (final var value : values()) {
                if (value.toString().equals(str))
                    return value;
            }
            return null;
        }
    }

    public Node(final @NotNull NodeType nodeType, final @NotNull String nodeId) {
        this.nodeType = nodeType;
        this.nodeId = nodeId;
    }

    public static Node from(final @NotNull org.neo4j.driver.types.Node neo4jNode) {
        final var nodeType = neo4jNode.get("nodeType").asString();
        final var nodeId = neo4jNode.get("nodeId").asString();

        if (nodeType == null || nodeType.isEmpty() || nodeId == null || nodeId.isEmpty()) {
            throw new InternalServerErrorException("Neo4J error: Incomplete node");
        }

        return new Node(NodeType.fromString(nodeType), nodeId);
    }

    @Override
    public String toString() {
        return "Node{nodeType=" + nodeType + ", nodeId='" + nodeId + "'}";
    }

    public String findCypher() {
        return "MATCH (n:" + nodeType + " {nodeId: '" + nodeId + "'})--(connected) RETURN connected";
    }

    public String createCypher() {
        return "MERGE (n:" + nodeType.toString() + " {nodeId: '" + nodeId + "'}) " +
               "ON CREATE SET n.nodeId = '" + nodeId + "', " +
               "n.nodeType = '" + nodeType.toString() + "' " +
               "RETURN n";
    }
}
