package com.nasa.asteral.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntegrationService {

	private final WebClient webClient;

	public <T> T doGetRequest(String endpoint, Map<String, String> queryParameters,
			Class<T> clazz) {
		try {
			if (endpoint == null || clazz == null) {
				throw new IllegalArgumentException("Endpoint and class cannot be null");
			}
			var uriBuilder = UriComponentsBuilder.fromUriString(endpoint);
			if (queryParameters != null) {
				queryParameters.forEach(uriBuilder::queryParam);
			}
			var uri = uriBuilder.build().toUri();

			return webClient.get()
					.uri(uri)
					.retrieve()
					.bodyToMono(clazz)
					.block();
		} catch (Exception e) {
			throw new RuntimeException("Fetching data from API failed.", e);
		}
	}

}
