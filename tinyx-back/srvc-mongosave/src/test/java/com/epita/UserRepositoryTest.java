package com.epita;

import com.epita.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    @Test
    public void testRepositoryExists() {
        UserRepository repo = new UserRepository();
        assertNotNull(repo);
    }
}
