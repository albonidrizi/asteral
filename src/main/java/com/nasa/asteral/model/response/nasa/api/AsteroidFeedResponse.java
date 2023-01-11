package com.nasa.asteral.model.response.nasa.api;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class AsteroidFeedResponse {
    
    @SerializedName("near_earth_objects")
    private Map<String, List<AsteroidResponse>> nearEarthObjects;

}
