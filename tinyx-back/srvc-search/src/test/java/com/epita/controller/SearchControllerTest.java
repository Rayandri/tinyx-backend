package com.epita.controller;

import com.epita.service.PostSearchService;
import com.epita.service.UserSearchService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private PostSearchService postSearchService;

    @Mock
    private UserSearchService userSearchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchPost() {
        String words = "test";
        String hashtags = "#test";
        Response expectedResponse = Response.ok().build();

        when(postSearchService.searchPost(words, hashtags)).thenReturn(expectedResponse);

        Response actualResponse = searchController.searchPost(words, hashtags);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testGetUserPosts() {
        UUID userId = UUID.randomUUID();
        Response expectedResponse = Response.ok().build();

        when(userSearchService.getUserPosts(userId)).thenReturn(expectedResponse);

        Response actualResponse = searchController.getUserPosts(userId);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }
}
