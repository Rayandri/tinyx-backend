package com.epita.controller;

import com.epita.controller.contract.UserEntityPublish;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import jakarta.inject.Inject;

@Startup
@ApplicationScoped
public class UserPublisher {
    private final PubSubCommands<UserEntityPublish> publisher;

    @Inject
    public UserPublisher(final RedisDataSource ds) {
        publisher = ds.pubsub(UserEntityPublish.class);
    }
    public void publish(final UserEntityPublish message) {
        publisher.publish("users", message);
    }
}
