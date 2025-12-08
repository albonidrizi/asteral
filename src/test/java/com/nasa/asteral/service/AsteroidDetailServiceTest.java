package com.nasa.asteral.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.nasa.asteral.model.response.dto.AsteroidDetailResponse;
import com.nasa.asteral.model.response.nasa.api.AsteroidResponse;
import com.nasa.asteral.model.response.nasa.api.EstimatedDiameterDetailResponse;
import com.nasa.asteral.model.response.nasa.api.EstimatedDiameterResponse;

@ExtendWith(MockitoExtension.class)
class AsteroidDetailServiceTest {

    @Mock
    private IntegrationService integrationService;

    @InjectMocks
    private AsteroidDetailService asteroidDetailService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(asteroidDetailService, "apiKey", "test-api-key");
        ReflectionTestUtils.setField(asteroidDetailService, "asteroidDetailsEndpoint", "http://test.url");
    }

    @Test
    void getAsteroidDetailsById_shouldReturnDetails() {
        // Arrange
        String refId = "12345";
        AsteroidResponse mockResponse = new AsteroidResponse();
        mockResponse.setReferenceId(refId);
        mockResponse.setName("Test Asteroid");
        mockResponse.setAbsoluteMagnitude(10.5);
        mockResponse.setCloseApprochData(Collections.emptyList());

        EstimatedDiameterDetailResponse detail = new EstimatedDiameterDetailResponse();
        detail.setEstimatedDiameterMin(1.0);
        detail.setEstimatedDiameterMax(2.0);

        EstimatedDiameterResponse diameter = new EstimatedDiameterResponse();
        diameter.setKilometers(detail);
        mockResponse.setEstimatedDiameter(diameter);

        when(integrationService.doGetRequest(any(), any(), eq(AsteroidResponse.class)))
                .thenReturn(mockResponse);

        // Act
        AsteroidDetailResponse result = asteroidDetailService.getAsteroidDetailsById(refId);

        // Assert
        assertNotNull(result);
        assertEquals(refId, result.getReferenceId());
        assertEquals("Test Asteroid", result.getName());
        assertEquals(1.0, result.getEstimatedDiameterMinKm());
        assertEquals(2.0, result.getEstimatedDiameterMaxKm());
    }
}
