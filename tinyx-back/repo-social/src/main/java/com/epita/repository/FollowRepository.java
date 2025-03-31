package com.epita.repository;

import com.epita.repository.entity.FollowRelation;
import com.epita.repository.entity.User;
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

    public void removeFollow(UUID followerId, UUID followedId) {
        delete(new FollowRelation(followerId, followedId));
    }
}
