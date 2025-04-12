package com.epita.repository;

import com.epita.controller.contracts.PostContentContract;
import com.epita.repository.entity.Post;
import com.epita.repository.entity.PostContent;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PostRepository implements PanacheMongoRepositoryBase<Post, UUID> {

    public List<Post> getAllPosts() {
        return listAll();
    }

    public List<Post> findPostsByAuthor(UUID userId) {
        return find("authorId", userId).list();
    }

    public Post findPostById(UUID id) {
        return findById(id);
    }

    public Post addPost(UUID user, PostContent content, UUID reply){
        Post post = new Post(user, content.getId(), reply);
        persist(post);

        return post;
    }

    public void deletePost(UUID id) {
        deleteById(id);
    }
}
