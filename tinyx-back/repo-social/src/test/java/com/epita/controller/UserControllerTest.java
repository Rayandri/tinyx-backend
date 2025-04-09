package com.epita.controller;

import com.epita.service.UserService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    public void testGetLikes() {
        Response expectedResponse = Response.ok().build();
        when(userService.getLikes()).thenReturn(expectedResponse);

        Response actualResponse = userController.getLikes();

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testGetUserLikes() {
        UUID userId = UUID.randomUUID();
        Response expectedResponse = Response.ok().build();
        when(userService.getUserLikes(userId)).thenReturn(expectedResponse);

        Response actualResponse = userController.getUserLikes(userId);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }
}
