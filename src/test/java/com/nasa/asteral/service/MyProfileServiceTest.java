package com.nasa.asteral.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nasa.asteral.model.response.dto.MyProfileResponse;

@ExtendWith(MockitoExtension.class)
class MyProfileServiceTest {

    @Mock
    private FavoriteAsteroidService favoriteAsteroidService;

    @InjectMocks
    private MyProfileService myProfileService;

    @BeforeEach
    void setUp() {
        // Mock is already initialized by @Mock annotation
    }

    @Test
    void getMyProfileDetails_withValidUsernameAndRole_shouldReturnProfileDetails() {
        // Arrange
        String username = "testuser";
        String role = "USER";
        long favoriteCount = 5L;

        when(favoriteAsteroidService.countFavoriteAsteroids(username)).thenReturn(favoriteCount);

        // Act
        MyProfileResponse result = myProfileService.getMyProfileDetails(username, role);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(role, result.getRole());
        assertEquals(favoriteCount, result.getNumberOfFavoriteAsteroids());
        verify(favoriteAsteroidService, times(1)).countFavoriteAsteroids(username);
    }

    @Test
    void getMyProfileDetails_withZeroFavorites_shouldReturnZeroCount() {
        // Arrange
        String username = "newuser";
        String role = "USER";

        when(favoriteAsteroidService.countFavoriteAsteroids(username)).thenReturn(0L);

        // Act
        MyProfileResponse result = myProfileService.getMyProfileDetails(username, role);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(role, result.getRole());
        assertEquals(0L, result.getNumberOfFavoriteAsteroids());
    }

    @Test
    void getMyProfileDetails_withDifferentRoles_shouldReturnCorrectRole() {
        // Arrange
        String username = "admin";
        String role = "ADMIN";

        when(favoriteAsteroidService.countFavoriteAsteroids(anyString())).thenReturn(10L);

        // Act
        MyProfileResponse result = myProfileService.getMyProfileDetails(username, role);

        // Assert
        assertNotNull(result);
        assertEquals("ADMIN", result.getRole());
    }
}
