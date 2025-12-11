package com.nasa.asteral.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings("null")
class IntegrationServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    @SuppressWarnings("rawtypes")
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private ResponseSpec responseSpec;

    @InjectMocks
    private IntegrationService integrationService;

    private static class TestResponse {
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        // Setup WebClient mock chain
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(java.net.URI.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    void doGetRequest_withValidParameters_shouldReturnResponse() {
        // Arrange
        String endpoint = "http://test.com/api";
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");

        TestResponse expectedResponse = new TestResponse();
        expectedResponse.setData("test data");

        when(responseSpec.bodyToMono(TestResponse.class))
                .thenReturn(Mono.just(expectedResponse));

        // Act
        TestResponse result = integrationService.doGetRequest(endpoint, params, TestResponse.class);

        // Assert
        assertNotNull(result);
        assertEquals("test data", result.getData());
        verify(webClient, times(1)).get();
    }

    @Test
    void doGetRequest_withNullEndpoint_shouldThrowException() {
        // Act & Assert
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> integrationService.doGetRequest(null, new HashMap<>(), TestResponse.class)
        );

        assertTrue(exception.getMessage().contains("Fetching data from API failed"));
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Endpoint and class cannot be null", exception.getCause().getMessage());
    }

    @Test
    void doGetRequest_withNullClass_shouldThrowException() {
        // Act & Assert
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> integrationService.doGetRequest("http://test.com", new HashMap<>(), null)
        );

        assertTrue(exception.getMessage().contains("Fetching data from API failed"));
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Endpoint and class cannot be null", exception.getCause().getMessage());
    }

    @Test
    void doGetRequest_withNullQueryParameters_shouldWork() {
        // Arrange
        String endpoint = "http://test.com/api";
        TestResponse expectedResponse = new TestResponse();
        expectedResponse.setData("test");

        when(responseSpec.bodyToMono(TestResponse.class))
                .thenReturn(Mono.just(expectedResponse));

        // Act
        TestResponse result = integrationService.doGetRequest(endpoint, null, TestResponse.class);

        // Assert
        assertNotNull(result);
        assertEquals("test", result.getData());
    }

    @Test
    void doGetRequest_whenApiCallFails_shouldThrowRuntimeException() {
        // Arrange
        String endpoint = "http://test.com/api";
        when(responseSpec.bodyToMono(TestResponse.class))
                .thenReturn(Mono.error(new RuntimeException("API Error")));

        // Act & Assert
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> integrationService.doGetRequest(endpoint, new HashMap<>(), TestResponse.class)
        );

        assertTrue(exception.getMessage().contains("Fetching data from API failed"));
    }
}
