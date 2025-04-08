package com.epita.controller;

import com.epita.repository.entity.PostEntity;
import com.epita.service.PostSearchService;
import com.epita.service.UserSearchService;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import io.quarkus.mongodb.runtime.MongoClients;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/api/search")
@ApplicationScoped
public class SearchController {

    @Inject
    PostSearchService postSearchService;

    @Inject
    UserSearchService userService;
    @Inject
    MongoClients mongoClients;

    //------------------- Post Search -------------------

    /**
     * Here are the endpoints for all post search
     */

    @GET
    @Path("/posts")
    public Response searchPost(@QueryParam("words") String words, @QueryParam("hashtags") String hashtags) {
        /*
         * Search all the posts following the string
         * FORMAT:
         * words = "word1 word2 word3 ..."
         * hashtags = "#hashtag1 #hashtag2 #hashtag3 ..."
         */
        return postSearchService.searchPost(words, hashtags);
    }

    @POST
    @Path("/posts/save")
    public Response savePost(@RequestBody PostEntity postEntity) {
        /*
        * save a post in the post table
        * */

        return postSearchService.savePost(postEntity);
    }

    //------------------- User Search -------------------

    @GET
    @Path("/posts/{id}")
    public Response getUserPosts(@PathParam("id") UUID id) {
        /*
         * Search in MongoDB all the posts made by this user
         */
        return userService.getUserPosts(id);
    }

    @GET
    @Path("/users/{name}")
    public Response getUser(@PathParam("name") String name) {
        /*
         * Search in MongoDB for the user that matches the "name" field
         * Here name refers to a regex that has to be used to look for users
         */
        return userService.getUser(name);
    }
}
