package com.epita.controller.contracts;

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
public class UserEntityPublish {

    public enum UserAction {
        FOLLOW,
        UNFOLLOW,
    }

    public UUID id;
    public String username;

    public List<UUID> following;
    public List<UUID> followers;

    public List<UUID> own_tweets; //own tweets and reposted tweets
    public List<UUID> liked_tweets;

    public Date updated_at;
    public Date created_at;

    UserAction user_action;

    // Handle following
    public UserEntityPublish(UUID followingId, UUID followerId, UserAction userAction) {
        this.id = followingId;
        this.followers = new ArrayList<>();
        this.followers.add(followingId);
        this.user_action = userAction;
    }
}
