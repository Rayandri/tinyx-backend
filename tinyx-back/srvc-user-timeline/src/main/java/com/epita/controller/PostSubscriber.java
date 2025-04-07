package com.epita.controller;

import com.epita.controller.contracts.PostContract;
import com.epita.service.UserTimelineService;
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
public class PostSubscriber implements Consumer<PostContract> {
    private final PubSubCommands.RedisSubscriber subscriber;

    @Inject
    UserTimelineService homeService;

    public PostSubscriber(final RedisDataSource ds) {
        subscriber = ds.pubsub(PostContract.class)
                .subscribe("posts", this);
    }
    @Override
    public void accept(final PostContract message) {
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