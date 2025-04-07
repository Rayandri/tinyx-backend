package com.epita.repository;

import com.epita.repository.entity.PostEntityPublish;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import jakarta.inject.Inject;
import io.quarkus.runtime.Startup;

@ApplicationScoped
@Startup
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
