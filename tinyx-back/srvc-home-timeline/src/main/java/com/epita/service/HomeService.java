package com.epita.service;

import com.epita.controller.contracts.PostContract;
import com.epita.repository.PostRepository;
import com.epita.repository.TimelineRepository;
import com.epita.repository.UserRepository;
import com.epita.controller.contracts.UserContract;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.jboss.logging.Logger;

@ApplicationScoped
public class HomeService {

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

        List<UUID> timeline = postRepository.getTimelineById(userId);

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
        // Cette fonction est appelée quand il y a un nouveau evenement sur un post (nouveau post, like, etc)
        // On doit mettre à jour la timeline de tous les followers de l'utilisateur qui a posté ou qui a liké

        // On recupere l'utilisateur qui a posté ou liké
        // On recupere la liste de ses followers
        // On ajoute le post à la timeline de chaque follower

        UserContract user = userRepository.getUser(message.author);
        List<UUID> followers = user.followers;

        for (UUID follower : followers) {
            timelineRepository.newUpdate(follower, message.id);
        }

        List<UUID> likes = message.getLikes();
        for (UUID follower : likes) {
            timelineRepository.newUpdate(follower, message.id);
        }


    }

    public void newUpdate(UserContract message) {
        // Cette fonction est appelée quand il y a un nouveau evenement sur un user (follow, etc)
        // Normalement on doit recuperer tous les posts du nouveau utilisateur followed et les ajouter à la timeline de l'utilisateur qui a follow

        // On recupere la liste de ses followers
        // On ajoute le post à la timeline de chaque follower


        //TODO: juste a gerer le cas où il y a un nouveau follower.
    }
}
