package com.epita.repository;

import com.epita.repository.entity.Post;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheMongoRepository<Post> {
}
