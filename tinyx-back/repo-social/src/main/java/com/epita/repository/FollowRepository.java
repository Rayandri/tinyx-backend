package com.epita.repository;

import com.epita.repository.entity.FollowRelation;
import com.epita.repository.entity.UserEntityPublish;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FollowRepository implements PanacheMongoRepositoryBase<FollowRelation, UUID> {

    @Inject
    UserPublisher userPublisher;

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
        userPublisher.publish(new UserEntityPublish(follow.followedID, follow.followerId, UserEntityPublish.UserAction.FOLLOW));
        persist(follow);
    }

    public void removeFollow(UUID followerId, UUID followedId) {
        userPublisher.publish(new UserEntityPublish(followedId, followerId, UserEntityPublish.UserAction.UNFOLLOW));
        delete(new FollowRelation(followerId, followedId));
    }
}
