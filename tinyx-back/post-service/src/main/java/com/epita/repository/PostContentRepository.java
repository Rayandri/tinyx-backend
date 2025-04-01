package com.epita.repository;

import com.epita.repository.entity.Post;
import com.epita.repository.entity.PostContent;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class PostContentRepository implements PanacheMongoRepositoryBase<PostContent, UUID> {

    public PostContent findPostContentById(UUID id) {
        return findById(id);
    }
}
