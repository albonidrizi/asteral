package com.nasa.asteral.model.response.nasa.api;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class AsteroidResponse {
	
	@SerializedName("neo_reference_id")
    private String referenceId;
	
    private String name;
    
    @SerializedName("absolute_magnitude_h")
    private double absoluteMagnitude;
    
    @SerializedName("estimated_diameter")
    private EstimatedDiameterResponse estimatedDiameter;
    
    @SerializedName("is_potentially_hazardous_asteroid")
    private boolean potentiallyHazardous;
    
    @SerializedName("close_approach_data")
    private List<CloseApprochResponse> closeApprochData;
    
    private boolean isFavorite;
  
}
