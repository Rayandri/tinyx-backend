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

    public List<PostEntity> searchPosts(List<String> wordList, List<String> hashtagList) throws IOException {

        if (wordList.isEmpty() && hashtagList.isEmpty()) {
            return List.of();
        }

        SearchRequest searchRequest = new SearchRequest("posts"); 
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        
        if (!wordList.isEmpty() && hashtagList.isEmpty()) {
            sourceBuilder.query(QueryBuilders.boolQuery()
                    .should(wordList.stream()
                            .map(word -> QueryBuilders.matchQuery("content", word))
                            .toArray(org.elasticsearch.index.query.QueryBuilder[]::new))
            );
        } else if (wordList.isEmpty() && !hashtagList.isEmpty()) {  
            sourceBuilder.query(QueryBuilders.boolQuery()
                    .must(hashtagList.stream()
                            .map(hashtag -> QueryBuilders.matchQuery("content", hashtag))
                            .toArray(org.elasticsearch.index.query.QueryBuilder[]::new))
            );
        } else if (!wordList.isEmpty() && !hashtagList.isEmpty()) {
            sourceBuilder.query(QueryBuilders.boolQuery()
                    .should(wordList.stream()
                            .map(word -> QueryBuilders.matchQuery("content", word))
                            .toArray(org.elasticsearch.index.query.QueryBuilder[]::new))
                    .must(hashtagList.stream()
                            .map(hashtag -> QueryBuilders.matchQuery("content", hashtag))
                            .toArray(org.elasticsearch.index.query.QueryBuilder[]::new))
            );
        }

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return response.hits()
            .hits()
            .stream()
            .map(hit -> hit.source())
            .toList();
    }
}