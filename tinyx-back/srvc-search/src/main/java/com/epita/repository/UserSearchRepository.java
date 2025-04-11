package com.epita.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.epita.repository.entity.PostEntity;
import com.epita.repository.entity.UserEntity;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserSearchRepository {
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

}
