package com.nasa.asteral.model.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyProfileResponse {
	
	private String username;
	
	private String role;
	
	private long numberOfFavoriteAsteroids;

}
