package com.epita.domain.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.epita.domain.entity.PostEntity;
import com.epita.domain.entity.UserEntity;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;


public class UserService {
    @Inject
    ElasticsearchClient elasticsearchClient;

    public List<UserEntity> aggregateUsers(String regex) {
        try{
            SearchRequest searchRequest = SearchRequest.of(sr -> sr
                    .index("users")
                    .query(q -> q
                            .regexp(r -> r
                                    .field("name")
                                    .value(regex)
                                    .caseInsensitive(true)
                            )
                    )
            );

            SearchResponse<UserEntity> response = elasticsearchClient.search(searchRequest, UserEntity.class);

            return response.hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public List<PostEntity> aggregatePosts(UUID id) {
        try {
            SearchRequest searchRequest = SearchRequest.of(sr -> sr
                    .index("posts")
                    .query(q -> q
                            .term(t -> t
                                    .field("userId")
                                    .value("ton_user_id") // Remplace par la vraie valeur de l'id
                            )
                    )
            );

            SearchResponse<PostEntity> response = elasticsearchClient.search(searchRequest, PostEntity.class);
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
