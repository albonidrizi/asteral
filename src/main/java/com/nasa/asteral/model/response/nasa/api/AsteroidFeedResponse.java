package com.nasa.asteral.model.response.nasa.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AsteroidFeedResponse {

    @JsonProperty("near_earth_objects")
    private Map<String, List<AsteroidResponse>> nearEarthObjects;

}
