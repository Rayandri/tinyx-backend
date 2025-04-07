package com.epita.repository;

import com.epita.repository.entity.Like;
import com.epita.repository.entity.PostEntityPublish;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import com.epita.repository.entity.Like;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LikeRepository implements PanacheMongoRepositoryBase<Like, UUID> {
    @Inject
    PostPublisher postPublisher;

    public Like findLikeById(UUID id) {
        return findById(id);
    }

    public List<Like> listAllLikes() {
        return listAll();
    }

    public List<Like> findLikesByUserId(UUID userId) {
        return find("user.id", userId).list();
    }

    public List<Like> findLikesByPostId(UUID postId) {
        return find("postId", postId).list();
    }

    public void addLike(UUID userId, UUID postId) {
        Like like = new Like(userId, postId);
        postPublisher.publish(new PostEntityPublish(userId, PostEntityPublish.PostAction.LIKED, postId));
        persist(like);
    }

    public void removeLike(UUID userId, UUID postId) throws NotFoundException {
        try {
            delete(new Like(userId, postId));
            postPublisher.publish(new PostEntityPublish(userId, PostEntityPublish.PostAction.UNLIKED, postId));
        }
        catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
