package com.epita.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.epita.repository.entity.PostContentEntity;
import com.epita.repository.entity.PostEntity;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@ApplicationScoped
public class PostSearchRepository implements PanacheMongoRepositoryBase<PostContentEntity, UUID> {

    @Inject
    ElasticsearchClient elasticsearchClient;

    public List<PostEntity> searchPosts(List<String> wordList, List<String> hashtagList) throws IOException {

        if (wordList.isEmpty() && hashtagList.isEmpty()) {
            return List.of();
        }

        return List.of();
    }
}
