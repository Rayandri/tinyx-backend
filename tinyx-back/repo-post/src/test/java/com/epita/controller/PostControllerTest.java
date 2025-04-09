package com.epita.controller;

import com.epita.service.PostService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPosts() {
        Response expectedResponse = Response.ok().build();
        when(postService.getPosts()).thenReturn(expectedResponse);

        Response actualResponse = postController.getPosts();

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testGetUserPosts() {
        UUID userId = UUID.randomUUID();
        Response expectedResponse = Response.ok().build();
        when(postService.getPostsFromUser(userId)).thenReturn(expectedResponse);

        Response actualResponse = postController.getUserPosts(userId);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }
}
