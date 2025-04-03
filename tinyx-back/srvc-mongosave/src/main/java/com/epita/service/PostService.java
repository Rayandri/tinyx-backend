package com.epita.service;

import com.epita.repository.PostRepository;
import com.epita.repository.entity.Post;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class PostService {

    @Inject
    PostRepository postRepository;

    public void addPost(Post post) {
        postRepository.persist(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.listAll();
    }
    
}
