package com.epita.controller;

import com.epita.repository.entity.Timeline;
import com.epita.service.UserTimelineService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserTimelineService userService;

    @InjectMocks
    private UserController userController;

    private UUID userId;
    private Timeline timeline;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        timeline = new Timeline();
        timeline.setUserId(userId);
    }

    @Test
    public void testGetFullTimeline() {
        when(userService.getUserTimeline(userId)).thenReturn(timeline);

        Timeline result = userController.getFullTimeline(userId);

        assertEquals(timeline, result);
        verify(userService, times(1)).getUserTimeline(userId);
    }

    @Test
    public void testGetPaginatedTimeline() {
        int page = 0;
        int size = 10;
        Date fromDate = new Date();
        Date toDate = new Date();

        when(userService.getUserTimeline(userId, page, size, fromDate, toDate)).thenReturn(timeline);

        Timeline result = userController.getPaginatedTimeline(userId, page, size, fromDate.toString(), toDate.toString());

        assertEquals(timeline, result);
        verify(userService, times(1)).getUserTimeline(userId, page, size, fromDate, toDate);
    }
}
