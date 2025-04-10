package com.epita;

import com.epita.controller.UserController;
import com.epita.repository.entity.User;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Test
    public void testCreateUserReturns201() {
        UserController controller = new UserController();
        User user = new User();
        user.setName("Test User");
        user.setBio("Bio");
        user.setPfp("https://pfp");

        controller.userService = mock(com.epita.service.UserService.class);

        Response response = controller.createUser(user);
        assertEquals(201, response.getStatus());
    }
}
