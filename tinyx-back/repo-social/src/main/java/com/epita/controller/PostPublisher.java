package com.epita.controller;

import com.epita.controller.contract.PostEntityPublish;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import jakarta.inject.Inject;

@Startup
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
