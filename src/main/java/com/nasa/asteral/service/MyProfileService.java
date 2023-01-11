package com.nasa.asteral.service;

import org.springframework.stereotype.Service;

import com.nasa.asteral.model.response.dto.MyProfileResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyProfileService {
	
	private final FavoriteAsteroidService favoriteAsteroidService;
	
	public MyProfileResponse getMyProfileDetails(String username, String role) {
		return MyProfileResponse
				.builder()
				.username(username)
				.role(role)
				.numberOfFavoriteAsteroids(favoriteAsteroidService.countFavoriteAsteroids(username))
				.build();
	}

}
