package com.epita.repository;

import com.epita.controller.contracts.PostContract;
import com.epita.controller.contracts.UserContract;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class PostRepository {
    Map<UUID, PostContract> posts = new HashMap<UUID, PostContract>();

    public List<UUID> getTimelineById(UUID userId) {
        // À faire par l'equipe DB :
        // 1. Recuperer les followers de l'utilisateur
        // 2. Recuperer les posts de ces followers
        // 3. Recuperer les posts liké par ces followers
        // 4. Trier les posts par date
        // 5. Retourner la liste des UUID des posts
        return new ArrayList<UUID>();
    }

    public PostContract getPost(UUID post) {
        return posts.get(post);
    }
}