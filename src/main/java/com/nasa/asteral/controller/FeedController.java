package com.nasa.asteral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import com.nasa.asteral.model.response.nasa.api.AsteroidFeedResponse;
import com.nasa.asteral.service.AsteroidFeedService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping({ "/", "/feed" })
@RequiredArgsConstructor
@Slf4j
public class FeedController extends BaseController {

	private final AsteroidFeedService feedService;

	@GetMapping("")
	public String getFeedView(Model model) {
		try {
			AsteroidFeedResponse asteroidResponse = feedService.getAsteroidFeed(extractUsername());
			model.addAttribute("asteroidResponse", asteroidResponse);
		} catch (Exception e) {
			log.error("Error loading asteroid feed", e);
			model.addAttribute("error",
					"Could not load asteroid feed. NASA API may be unavailable. Please try again later.");
			model.addAttribute("asteroidResponse", new AsteroidFeedResponse());
		}

		return ok("index", model);
	}

}
