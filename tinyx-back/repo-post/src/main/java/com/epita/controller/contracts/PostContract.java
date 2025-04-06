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

    public UUID id;
    public UUID author;

    public List<UUID> likes;
    public List<UUID> retweets;

    public PostContentContract content;

    public Date created_at;

    public PostAction action;


    public static PostContract createPost(UUID author, PostContentContract content) {
        PostContract postContract = new PostContract();
        postContract.created_at = new Date();
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
        postContract.created_at = post.getCreatedAt();
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