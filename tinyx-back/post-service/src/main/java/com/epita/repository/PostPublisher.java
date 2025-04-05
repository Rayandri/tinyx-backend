package com.epita.repository;

import com.epita.controller.contracts.PostContract;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostPublisher {

    private final PubSubCommands<PostContract> publisher;

    @Inject
    public PostPublisher(final RedisDataSource ds) {
        publisher = ds.pubsub(PostContract.class);
    }

    public void publish(final PostContract post)
    {
        publisher.publish("posts", post);
    }
}
