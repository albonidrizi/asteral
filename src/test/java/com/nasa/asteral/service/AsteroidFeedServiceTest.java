package com.nasa.asteral.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.nasa.asteral.model.response.nasa.api.AsteroidResponse;
import com.nasa.asteral.model.response.nasa.api.AsteroidFeedResponse;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
class AsteroidFeedServiceTest {

    @Mock
    private IntegrationService integrationService;

    @Mock
    private FavoriteAsteroidService favoriteAsteroidService;

    @InjectMocks
    private AsteroidFeedService asteroidFeedService;

    private AsteroidFeedResponse mockResponse;

    @BeforeEach
    void setUp() {
        // Set properties using reflection
        ReflectionTestUtils.setField(asteroidFeedService, "apiKey", "test-api-key");
        ReflectionTestUtils.setField(asteroidFeedService, "asteroidFeedEndpoint", "https://api.nasa.gov/neo/rest/v1/feed");

        // Create mock asteroid response
        mockResponse = new AsteroidFeedResponse();
        Map<String, List<AsteroidResponse>> nearEarthObjects = new LinkedHashMap<>();
        
        AsteroidResponse asteroid1 = new AsteroidResponse();
        asteroid1.setReferenceId("123456");
        asteroid1.setName("Test Asteroid 1");
        
        AsteroidResponse asteroid2 = new AsteroidResponse();
        asteroid2.setReferenceId("789012");
        asteroid2.setName("Test Asteroid 2");
        
        nearEarthObjects.put("2025-01-10", List.of(asteroid1, asteroid2));
        mockResponse.setNearEarthObjects(nearEarthObjects);
    }

    @Test
    void getAsteroidFeed_withAuthenticatedUser_shouldSetFavoriteFlags() {
        // Arrange
        String username = "testuser";
        
        when(integrationService.doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class)))
                .thenReturn(mockResponse);
        when(favoriteAsteroidService.isFavoriteAsteroid("123456", username)).thenReturn(true);
        when(favoriteAsteroidService.isFavoriteAsteroid("789012", username)).thenReturn(false);

        // Act
        AsteroidFeedResponse result = asteroidFeedService.getAsteroidFeed(username);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getNearEarthObjects());
        List<AsteroidResponse> asteroids = result.getNearEarthObjects().get("2025-01-10");
        assertTrue(asteroids.get(0).isFavorite());
        assertFalse(asteroids.get(1).isFavorite());
        
        verify(integrationService, times(1)).doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class));
        verify(favoriteAsteroidService, times(1)).isFavoriteAsteroid("123456", username);
        verify(favoriteAsteroidService, times(1)).isFavoriteAsteroid("789012", username);
    }

    @Test
    void getAsteroidFeed_withNullUsername_shouldNotSetFavoriteFlags() {
        // Arrange
        when(integrationService.doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class)))
                .thenReturn(mockResponse);

        // Act
        AsteroidFeedResponse result = asteroidFeedService.getAsteroidFeed(null);

        // Assert
        assertNotNull(result);
        verify(integrationService, times(1)).doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class));
        verify(favoriteAsteroidService, never()).isFavoriteAsteroid(anyString(), anyString());
    }

    @Test
    void getAsteroidFeed_withEmptyUsername_shouldNotSetFavoriteFlags() {
        // Arrange
        when(integrationService.doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class)))
                .thenReturn(mockResponse);

        // Act
        AsteroidFeedResponse result = asteroidFeedService.getAsteroidFeed("");

        // Assert
        assertNotNull(result);
        verify(integrationService, times(1)).doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class));
        verify(favoriteAsteroidService, never()).isFavoriteAsteroid(anyString(), anyString());
    }

    @Test
    void getAsteroidFeed_shouldCallIntegrationServiceWithCorrectParameters() {
        // Arrange
        when(integrationService.doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class)))
                .thenReturn(mockResponse);

        // Act
        asteroidFeedService.getAsteroidFeed("testuser");

        // Assert
        verify(integrationService).doGetRequest(
            eq("https://api.nasa.gov/neo/rest/v1/feed"),
            argThat(params -> {
                Map<String, String> map = (Map<String, String>) params;
                return map.containsKey("start_date") && 
                       map.containsKey("end_date") && 
                       "test-api-key".equals(map.get("api_key"));
            }),
            eq(AsteroidFeedResponse.class)
        );
    }

    @Test
    void getAsteroidFeed_withAnonymousUser_shouldReturnFeedWithoutFavorites() {
        // Arrange
        AsteroidFeedResponse expectedResponse = new AsteroidFeedResponse();
        Map<String, List<AsteroidResponse>> nearEarthObjects = new HashMap<>();
        
        AsteroidResponse asteroid = new AsteroidResponse();
        asteroid.setReferenceId("111111");
        nearEarthObjects.put("2025-01-10", List.of(asteroid));
        expectedResponse.setNearEarthObjects(nearEarthObjects);

        when(integrationService.doGetRequest(anyString(), anyMap(), eq(AsteroidFeedResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        AsteroidFeedResponse result = asteroidFeedService.getAsteroidFeed(null);

        // Assert
        assertNotNull(result);
        assertFalse(result.getNearEarthObjects().isEmpty());
        verify(favoriteAsteroidService, never()).isFavoriteAsteroid(anyString(), anyString());
    }
}

