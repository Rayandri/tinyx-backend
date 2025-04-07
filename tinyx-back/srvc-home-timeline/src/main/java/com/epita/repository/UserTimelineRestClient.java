package com.epita.repository;

import com.epita.repository.entity.Timeline;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@RegisterRestClient(configKey = "usertimeline-api")
public interface UserTimelineRestClient {
    @GET
    @Path("/api/timeline/user")
    @Produces(MediaType.APPLICATION_JSON)
    Timeline getUserTimeline(UUID userId);
}
