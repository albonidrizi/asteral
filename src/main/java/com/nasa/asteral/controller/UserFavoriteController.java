package com.nasa.asteral.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nasa.asteral.model.response.dto.AsteroidDetailResponse;
import com.nasa.asteral.service.FavoriteAsteroidService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/favorite")
@RequiredArgsConstructor
public class UserFavoriteController extends BaseController {
	
	private final FavoriteAsteroidService favoriteService;
	
	@GetMapping("/add/{referenceId}")
	public String addAsteroidToFavorite(@PathVariable String referenceId, Model model) {
		favoriteService.addAsteroidToFavorite(referenceId, extractUsername());
		return ok("redirect:/", model);
	}
	
	@GetMapping("/remove/{referenceId}")
	public String removeAsteroidFromFavorite(@PathVariable String referenceId, 
			@RequestParam String redirectPage,
			Model model) {
		favoriteService.removeAsteroidFromFavorite(referenceId, extractUsername());
		
		return ok("redirect:/" + redirectPage, model);
	}

	@GetMapping("/all")
	public String getFavoriteAsteroidsView(Model model) {
		List<AsteroidDetailResponse> favoriteAsteroids = favoriteService.getFavoriteAsteroids(extractUsername());
		model.addAttribute("favoriteAsteroids", favoriteAsteroids);
		
		return ok("myFavorites", model);
	}
	
	
}
