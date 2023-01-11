package com.nasa.asteral.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static String getDateAsString(LocalDate localDate) {
		return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(localDate);
	}
	
	public static LocalDate getStringAsDate(String s) {
		return LocalDate.parse(s, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}
	
	public static LocalDate getTodaysDateIgnoringTime() {
		return LocalDate.now().atStartOfDay().toLocalDate();
	}


}
