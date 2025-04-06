package com.epita.controller;

import com.epita.service.PostSearchService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/api/search/post")
@ApplicationScoped
public class PostSearchController {
    /*
    * here are the endpoints for all post search
    * */

    @Inject
    PostSearchService postSearchService;

    @GET
    @Path("/")
    public Response searchPost(@QueryParam("words") String words, @QueryParam("hashtags") String hashtags) {
        /*
        *
        * search all the post following the string
        * FORMAT:
        * words = "word1 word2 word3 ..."
        * hashtags = "#hashtag1 #hashtag2 #hashtag3 ..."
        *
        */
        return postSearchService.searchPost(words, hashtags);
    }
}
