package com.nasa.asteral.model.response.nasa.api;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CloseApprochResponse {
	
	@SerializedName("close_approach_date")
	private String closeApprochDate;
	
	@SerializedName("orbiting_body")
	private String orbitingBody;

}
