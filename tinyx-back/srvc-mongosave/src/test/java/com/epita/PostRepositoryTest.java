package com.epita;

import com.epita.repository.PostRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostRepositoryTest {

    @Test
    public void testRepositoryExists() {
        PostRepository repo = new PostRepository();
        assertNotNull(repo);
    }
}
