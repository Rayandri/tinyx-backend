package com.epita.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.epita.repository.entity.PostContentEntity;
import com.epita.repository.entity.PostEntity;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PostSearchRepository implements PanacheMongoRepositoryBase<PostContentEntity, UUID> {

    @Inject
    ElasticsearchClient elasticsearchClient;

    public List<PostEntity> searchPost(List<String> wordsList, List<String> hashtagsList) {

        //#TODO: Implement the elastic search logic here
        // You got this Alex!! :)

        return null;
    }
}
