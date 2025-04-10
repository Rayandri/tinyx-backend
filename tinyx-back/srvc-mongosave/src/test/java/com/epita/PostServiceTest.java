package com.epita;

import com.epita.service.PostService;
import com.epita.repository.PostRepository;
import com.epita.repository.entity.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Test
    public void testAddPostCallsPersist() {
        PostRepository mockRepo = mock(PostRepository.class);
        PostService service = new PostService();
        service.postRepository = mockRepo;

        Post post = new Post();
        service.addPost(post);

        verify(mockRepo, times(1)).persist(post);
    }
}
