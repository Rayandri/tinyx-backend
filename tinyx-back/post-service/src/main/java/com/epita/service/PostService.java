package com.epita.service;

import com.epita.controller.contracts.PostContentContract;
import com.epita.controller.contracts.PostContract;
import com.epita.repository.PostContentRepository;
import com.epita.repository.PostPublisher;
import com.epita.repository.PostRepository;
import com.epita.repository.entity.Post;
import com.epita.repository.entity.PostContent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PostService {
    
    @Inject
    PostRepository postRepository;

    @Inject
    PostContentRepository postContentRepository;
    
    @Inject
    PostPublisher publisher;
    
    public Response getPosts() {

        //Ensure there are posts
        List<Post> posts = postRepository.getAllPosts();
        if (posts.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No posts found").build();
        }
        return Response.ok(posts).build();
    }

    public Response getPostsFromUser(UUID authorId) {

        // Ensure there are posts from the given user
        List<Post> posts = postRepository.findPostsByAuthor(authorId);
        if (posts.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No posts found for this user").build();
        }
        return Response.ok(posts).build();
    }

    public Response getPostFromId(UUID id) {

        // Ensure post exists
        Post post = postRepository.findPostById(id);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post not found").build();
        }
        return Response.ok(post).build();
    }

    public Response getPostReply(UUID postId) {

        // Check post exists
        Post post = postRepository.findPostById(postId);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post not found").build();
        }

        // Check reply exists
        if (post.getReplyTo() == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("This message has no replies").build();
        }

        // Check reply message exists
        PostContent reply = postContentRepository.findPostContentById(post.getReplyTo());
        if (reply == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Reply message not found").build();
        }
        return Response.ok(reply).build();
    }

    public Response addPost(UUID userId, PostContentContract content) {
        // Create post contract
        PostContract postContract = PostContract.createPost(userId, content);

        // Publish it to redis queue
        publisher.publish(postContract);

        //Check reply message exists
        if (postRepository.findPostById(content.replyTo) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Reply message not found").build();
        }

        // Check if the post content is valid
        PostContent postContent = new PostContent(content.getContent(), content.getMedia(),content.getRepost());
        try {
            postRepository.addPost(userId, postContent, content.replyTo);
        }
        catch(IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }

    public Response deletePost(UUID userId, UUID postId) {
        // Fetch post
        Post post = postRepository.findPostById(postId);

        // Check if post exists
        if(post == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post not found").build();
        }

        // Check if post is authored by the user
        if(post.getAuthorId() != userId) {
            return Response.status(Response.Status.FORBIDDEN).entity("You are not the author of this post").build();
        }

        PostContract postContract = PostContract.deletePost(post);
        publisher.publish(postContract);
        postRepository.deletePost(postId);

        return Response.ok().build();
    }
}
