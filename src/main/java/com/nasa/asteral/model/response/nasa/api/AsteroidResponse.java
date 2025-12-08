package com.nasa.asteral.model.response.nasa.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AsteroidResponse {

    @JsonProperty("neo_reference_id")
    private String referenceId;

    private String name;

    @JsonProperty("absolute_magnitude_h")
    private double absoluteMagnitude;

    @JsonProperty("estimated_diameter")
    private EstimatedDiameterResponse estimatedDiameter;

    @JsonProperty("is_potentially_hazardous_asteroid")
    private boolean potentiallyHazardous;

    @JsonProperty("close_approach_data")
    private List<CloseApprochResponse> closeApprochData;

    private boolean isFavorite;

}
