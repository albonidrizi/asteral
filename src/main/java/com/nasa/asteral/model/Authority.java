package com.nasa.asteral.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
	
	ANONYMOUS("ANONYMOUS"),
	USER("USER");
	
	private String value;

}
