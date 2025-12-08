package com.nasa.asteral.model.response.nasa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EstimatedDiameterDetailResponse {

	@JsonProperty("estimated_diameter_min")
	private double estimatedDiameterMin;

	@JsonProperty("estimated_diameter_max")
	private double estimatedDiameterMax;

}
