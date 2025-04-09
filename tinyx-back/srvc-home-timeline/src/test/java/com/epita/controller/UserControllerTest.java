package com.epita.controller;

import com.epita.repository.entity.Timeline;
import com.epita.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFullTimeline() {
        UUID userId = UUID.randomUUID();
        Timeline expectedTimeline = new Timeline();
        when(userService.getHomeTimeline(userId)).thenReturn(expectedTimeline);

        Timeline actualTimeline = userController.getFullTimeline(userId);

        assertEquals(expectedTimeline, actualTimeline);
        verify(userService, times(1)).getHomeTimeline(userId);
    }

    @Test
    public void testGetPaginatedTimeline() {
        UUID userId = UUID.randomUUID();
        int page = 0;
        int size = 10;
        String fromDateStr = "2022-01-01";
        String toDateStr = "2022-12-31";
        Timeline expectedTimeline = new Timeline();
        when(userService.getHomeTimeline(userId, page, size, null, null)).thenReturn(expectedTimeline);

        Timeline actualTimeline = userController.getPaginatedTimeline(userId, page, size, fromDateStr, toDateStr);

        assertEquals(expectedTimeline, actualTimeline);
        verify(userService, times(1)).getHomeTimeline(userId, page, size, null, null);
    }
}
