package com.epita.service;

import com.epita.controller.contracts.PostContract;
import com.epita.repository.PostRepository;
import com.epita.repository.TimelineRepository;
import com.epita.repository.UserRepository;
import com.epita.controller.contracts.UserContract;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.*;

import org.jboss.logging.Logger;

@ApplicationScoped
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(HomeService.class);

    @Inject
    PostRepository postRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    TimelineRepository timelineRepository;


    public Response getTimeline(UUID userId) {
        // fetch les posts de ses followers
        // fetch les posts liké par ses followers

        // on fait la liste de toute la TL
        // on trie par date

        List<UUID> timeline = postRepository.GetTimelineByUserId(userId);

        // On l'inscrit dans la Map des timelines Map[id] = List vide
        timelineRepository.subscribeToTimeline(userId);
        // on return tout

        return Response.ok(timeline).build();
    }

    public Response getUpdateOfTheTimeLine(UUID userId) {
        // fetch la timeline qui est la Map de timelines
        List<UUID> timeline = timelineRepository.getTimelineById(userId);
        timelineRepository.deleteTimeline(userId);
        return Response.ok(timeline).build();
    }

    public void newUpdate(PostContract message) {

    }
    public void newUpdate(UserContract user) {
        if (user.user_action == UserContract.UserAction.FOLLOW)
        {
            if (user.followers.size() == 1) {
                timelineRepository.subscribeToTimeline(user.followers.get(0));
            }
        } else if (user.user_action == UserContract.UserAction.UNFOLLOW) {
            if (user.followers.size() == 1) {
                timelineRepository.deleteTimeline(user.followers.get(0));
            }
        }
    }
}
