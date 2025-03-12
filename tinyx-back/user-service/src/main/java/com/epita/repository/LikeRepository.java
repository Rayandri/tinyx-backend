package com.epita.repository;

import com.epita.repository.entity.Like;
import com.epita.repository.entity.User;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LikeRepository implements PanacheMongoRepositoryBase<Like, UUID> {

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
        persist(like);
    }

    public void addLike(Like like) {
        persist(like);
    }
}
