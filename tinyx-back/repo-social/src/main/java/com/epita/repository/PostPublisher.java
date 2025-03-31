package com.epita.repository;

import com.epita.controller.contracts.PostEntityPublish;
import com.epita.controller.contracts.UserEntityPublish;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostPublisher {
    private final PubSubCommands<PostEntityPublish> publisher;

    @Inject
    public PostPublisher(final RedisDataSource ds) {
        publisher = ds.pubsub(PostEntityPublish.class);
    }
    public void publish(final PostEntityPublish message) {
        publisher.publish("posts", message);
    }
}
