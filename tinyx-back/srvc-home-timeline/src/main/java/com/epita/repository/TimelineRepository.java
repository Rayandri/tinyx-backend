package com.epita.repository;

import com.epita.controller.contracts.PostContract;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;

// To other dev: No touch this
@ApplicationScoped
public class TimelineRepository {

    @Inject
    PostRepository postRepository;

    public Map<UUID, List<UUID>> timelines = new HashMap<>(); //key : UUID d'user, value: liste des UUID des posts de la timeline

    public void subscribeToTimeline(UUID userId) {
        timelines.put(userId, new ArrayList<UUID>());
    }

    public void newUpdate(UUID userId, UUID post) {
        if (!timelines.containsKey(userId)) {
            return;
        }
        timelines.get(userId).add(post);
    }

    public List<UUID> getTimelineById(UUID userId) {
        //on doit le sort par date

        List<UUID> timeline = timelines.get(userId);

        List<PostContract> posts = new ArrayList<>();
        for (UUID post : timeline) {
            posts.add(postRepository.getPost(post));
        }
        posts.sort((p1, p2) -> p2.updated_at.compareTo(p1.updated_at));
        timeline.clear();
        for (PostContract post : posts) {
            timeline.add(post.id);
        }
        return timeline;
    }

    public void deleteTimeline(UUID userId) {
        timelines.remove(userId);
    }

    public boolean isExist(UUID userId) {
        return timelines.containsKey(userId);
    }
}