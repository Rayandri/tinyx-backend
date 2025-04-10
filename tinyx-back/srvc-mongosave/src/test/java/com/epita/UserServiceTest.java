package com.epita;

import com.epita.service.UserService;
import com.epita.repository.UserRepository;
import com.epita.repository.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void testAddUserCallsPersist() {
        UserRepository mockRepo = mock(UserRepository.class);
        UserService service = new UserService();
        service.userRepository = mockRepo;

        User user = new User();
        service.addUser(user);

        verify(mockRepo, times(1)).persist(user);
    }
}
