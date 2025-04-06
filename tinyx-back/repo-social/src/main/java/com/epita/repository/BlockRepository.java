package com.epita.entity;

import com.epita.entity.entity.BlockRelation;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BlockRepository implements PanacheMongoRepositoryBase<BlockRelation, UUID> {

    public BlockRelation findBlockById(UUID id) {
        return findById(id);
    }

    public List<BlockRelation> listAllBlocks() {
        return listAll();
    }

    public List<BlockRelation> getAllBlocked(UUID blockerId) {
        return find("blockerId", blockerId).list();
    }

    public List<BlockRelation> getAllBlockers(UUID blockedId) {
        return find("blockedId", blockedId).list();
    }

    public void addBlock(BlockRelation block) {
        persist(block);
    }

    public void removeBlock(UUID userId, UUID blockId) {
        delete(new BlockRelation(userId, blockId));
    }
}
