package com.nasa.asteral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nasa.asteral.service.AsteroidDetailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/asteroid")
@RequiredArgsConstructor
public class AsteroidDetailController extends BaseController {
	
	private final AsteroidDetailService asteroidDetailService;
	
	@GetMapping("/{referenceId}")
	public String getAsteroidDetailsView(@PathVariable String referenceId, Model model) {
		model.addAttribute("asteroidResponse", asteroidDetailService.getAsteroidDetailsById(referenceId));
		
		return ok("asteroidDetails", model);
	}

}
