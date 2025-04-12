package com.epita.repository;

import com.epita.repository.entity.FollowEntry;
import com.epita.repository.entity.Timeline;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.UUID;

@RegisterRestClient(configKey = "reposocial-api")
public interface RepoSocialRestClient {
    @GET
    @Path("/api/user/followers")
    @Produces(MediaType.APPLICATION_JSON)
    Response getFollowers(@HeaderParam("X-user-id") UUID userId);
}
