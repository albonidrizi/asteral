package com.nasa.asteral.model.response.nasa.api;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class EstimatedDiameterDetailResponse {
	
	@SerializedName("estimated_diameter_min")
	private double estimatedDiameterMin;
	
	@SerializedName("estimated_diameter_max")
	private double estimatedDiameterMax;

}
