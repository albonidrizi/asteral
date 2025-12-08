package com.nasa.asteral.model.response.nasa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CloseApprochResponse {

	@JsonProperty("close_approach_date")
	private String closeApprochDate;

	@JsonProperty("orbiting_body")
	private String orbitingBody;

}
