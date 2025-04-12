package com.epita.controller;

import com.epita.repository.entity.Timeline;
import com.epita.service.UserService;
import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Path("/api/timeline")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("/home")
    public Timeline getFullTimeline(@HeaderParam("X-user-id") UUID userId) {
        return userService.getHomeTimeline(userId);
    }

    @GET
    @Path("/home/page")
    public Timeline getPaginatedTimeline(
            @HeaderParam("X-user-id") UUID userId,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("fromDate") String fromDateStr,
            @QueryParam("toDate") String toDateStr) {

        Date fromDate = parseDate(fromDateStr);
        Date toDate = parseDate(toDateStr);
        return userService.getHomeTimeline(userId, page, size, fromDate, toDate);
    }


    @GET
    @Path("/home/all")
    public List<Timeline> getAllTimeline() {
        return userService.getAllTimelines();
    }

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new BadRequestException("Date invalide. Use this format yyyy-MM-dd.");
        }
    }
}
