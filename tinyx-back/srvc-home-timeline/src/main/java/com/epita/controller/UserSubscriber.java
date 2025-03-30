package com.epita.controller;

import com.epita.controller.contracts.PostContract;
import com.epita.controller.contracts.UserContract;
import com.epita.service.HomeService;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.function.Consumer;

import static io.quarkus.mongodb.runtime.dns.MongoDnsClientProvider.vertx;

@Startup
@ApplicationScoped
public class UserSubscriber implements Consumer<UserContract> {
    private final PubSubCommands.RedisSubscriber subscriber;

    @Inject
    HomeService homeService;

    public UserSubscriber(final RedisDataSource ds) {
        subscriber = ds.pubsub(UserContract.class)
                .subscribe("users", this);
    }
    @Override
    public void accept(final UserContract message) {
        vertx.executeBlocking(future-> {
            homeService.newUpdate(message);
            future.complete();
        });
    }

    @PreDestroy
    public void terminate() {
        subscriber.unsubscribe();
    }
}