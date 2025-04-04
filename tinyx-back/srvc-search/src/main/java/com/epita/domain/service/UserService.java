package com.epita.domain.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import jakarta.inject.Inject;

import javax.swing.text.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class UserService {
    @Inject
    ElasticsearchClient elasticsearchClient;

    public List<UserService> aggregateUsers(String regex) {
        try{
            SearchRequest searchRequest = SearchRequest.of(sr -> sr
                    .index("user")
                    .query(q -> q
                            .regexp(r -> r
                                    .field("name")
                                    .value(regex)
                                    .caseInsensitive(true)
                            )
                    )
            );

            SearchResponse<UserService> response = elasticsearchClient.search(searchRequest, UserService.class);

            return response.hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }
}
