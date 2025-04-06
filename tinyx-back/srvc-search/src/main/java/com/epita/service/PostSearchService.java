package com.epita.service;

import com.epita.repository.entity.PostEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.epita.repository.PostSearchRepository;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PostSearchService {

    @Inject
    PostSearchRepository postSearchRepository;


    public Response searchPost(String words, String hashtags)
    {

        // Check if the input is null
        if ((words == null || words.isEmpty()) && (hashtags == null || hashtags.isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameters.").build();
        }

        // Fetch appropriate posts
        List<String> wordsList =  words != null ? List.of(words.split(" ")) : new ArrayList<>();
        List<String> hashtagsList = hashtags != null ? List.of(hashtags.split(" ")): new ArrayList<>();

        List<PostEntity> posts = postSearchRepository.searchPost(wordsList, hashtagsList);

        // Ensure list not empty
        if (posts.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No post found!").build();
        }

        return Response.ok(posts).build();
    }

}
