package com.nasa.asteral.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // In a real production app, use a logger here (e.g., SLF4J)
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
        return "error"; // Ensure you have an error.html template or use a default
    }
}
