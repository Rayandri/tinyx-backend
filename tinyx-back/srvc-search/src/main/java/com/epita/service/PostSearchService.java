package com.epita.service;

import com.epita.repository.entity.PostEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.epita.repository.PostSearchRepository;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Response savePost(PostEntity postEntity) {

        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/posts")) {
            MongoDatabase database = mongoClient.getDatabase("tinyX");
            MongoCollection<Document> collection = database.getCollection("posts");

            Document post = new Document("id", UUID.randomUUID())
                    .append("author", postEntity.getAuthorId())
                    .append("content", postEntity.getContent())
                    .append("time", postEntity.getCreatedAt());

            collection.insertOne(post);
            return Response.ok().build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
