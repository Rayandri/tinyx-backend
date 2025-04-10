package com.epita;

import com.epita.controller.PostController;
import com.epita.repository.entity.Post;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Test
    public void testCreatePostReturns201() {
        PostController controller = new PostController();
        Post post = new Post();
        post.setContent("My post");
        post.setUserId("uuid");

        controller.postService = mock(com.epita.service.PostService.class);

        Response response = controller.createPost(post);
        assertEquals(201, response.getStatus());
    }
}
