package com.epita.entity;

import com.epita.entity.entity.FollowRelation;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FollowRepository implements PanacheMongoRepositoryBase<FollowRelation, UUID> {

    public FollowRelation findFollowById(UUID id) {
        return findById(id);
    }

    public List<FollowRelation> listAllFollows() {
        return listAll();
    }

    public List<FollowRelation> getAllFollowed(UUID followerId) {
        return find("followerId", followerId).list();
    }

    public List<FollowRelation> getAllFollowers(UUID followedId) {
        return find("followedId", followedId).list();
    }

    public void addFollow(FollowRelation follow) {
        persist(follow);
    }
}
