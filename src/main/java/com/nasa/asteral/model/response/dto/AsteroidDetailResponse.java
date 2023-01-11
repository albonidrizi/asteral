package com.nasa.asteral.model.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsteroidDetailResponse {
	
	private String referenceId;
	
	private String name;
	
	private double absoluteMagnitude;

	private double estimatedDiameterMinKm;
	
	private double estimatedDiameterMaxKm;
	
	private boolean potentiallyHazardous;
	
	private String orbitingBody;
	
	private String lastCloseApproachingDate;
	
}
