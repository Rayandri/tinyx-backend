package com.epita.controller.contracts;

import com.epita.repository.entity.Post;
import com.epita.repository.entity.PostContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostContract {

    public enum PostAction {
        CREATE,
        DELETE,
        LIKED,
        UNLIKED,
    }

    private UUID id;
    private UUID author;

    private List<UUID> likes;
    private List<UUID> retweets;

    private PostContentContract content;

    private Date createdAt;
    private Date updatedAt;

    private PostAction action;


    public static PostContract createPost(UUID author, PostContentContract content) {
        PostContract postContract = new PostContract();
        postContract.createdAt = new Date();
        postContract.updatedAt = new Date();
        postContract.author = author;
        postContract.id = UUID.randomUUID();
        postContract.likes = new ArrayList<>();
        postContract.retweets = new ArrayList<>();
        postContract.content = content;
        postContract.action = PostAction.CREATE;
        return postContract;
    }

    public static PostContract deletePost(Post post) {
        PostContract postContract = new PostContract();
        postContract.createdAt = post.getCreatedAt();
        postContract.updatedAt = new Date();
        postContract.author = post.getAuthorId();
        postContract.id = post.getId();
        postContract.likes = new ArrayList<>();
        postContract.retweets = new ArrayList<>();
        //should not matter if it is null or not
        postContract.content = null;
        postContract.action = PostAction.DELETE;
        return postContract;
    }
}