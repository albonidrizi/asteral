package com.nasa.asteral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nasa.asteral.model.response.nasa.api.AsteroidFeedResponse;
import com.nasa.asteral.service.AsteroidFeedService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping({"/", "/feed"})
@RequiredArgsConstructor
public class FeedController extends BaseController {
	
	private final AsteroidFeedService feedService;
	
	@GetMapping("")
	public String getFeedView(Model model) {
		AsteroidFeedResponse asteroidResponse = feedService.getAsteroidFeed(extractUsername());
		model.addAttribute("asteroidResponse", asteroidResponse);
		
		return ok("index", model);
	}

}
