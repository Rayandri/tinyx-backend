package com.epita.controller;

import com.epita.controller.contracts.CreateUserRequest;
import com.epita.controller.contracts.LoginRequest;
import com.epita.service.AuthService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    AuthService authService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest("testuser", "password");
        when(authService.createUser(any(String.class), any(String.class))).thenReturn(Response.ok().build());

        Response response = userController.createUser(request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testLogin() {
        LoginRequest request = new LoginRequest("testuser", "password");
        when(authService.login(any(String.class), any(String.class))).thenReturn(Response.ok().build());

        Response response = userController.login(request);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
