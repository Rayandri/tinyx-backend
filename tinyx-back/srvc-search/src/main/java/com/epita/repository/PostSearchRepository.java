package com.epita.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.epita.repository.entity.PostEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class PostSearchRepository {

    @Inject
    ElasticsearchClient elasticsearchClient;

    public List<PostEntity> searchPosts(List<String> wordList, List<String> hashtagList) {

        if (wordList.isEmpty() && hashtagList.isEmpty()) {
            return List.of();
        }

        final String regex;

        if (hashtagList.isEmpty()) {
            regex = "\\b(" + String.join("|", wordList) + ")\\b";
        } else {
            regex = "";
        }

        try {
            var response = elasticsearchClient.search(s -> s
                            .index("posts")
                            .query(q -> q
                                    .regexp(r -> r
                                            .field("content")
                                            .value(regex)
                                    )
                            ),
                            PostEntity.class
                    );

            List<PostEntity> posts = response.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());

            return List.of();
        } catch (IOException e) {
            return List.of();
        }
    }
}