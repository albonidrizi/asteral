package com.nasa.asteral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nasa.asteral.service.MyProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/profile")
@RequiredArgsConstructor
public class UserProfileController extends BaseController {
	
	private final MyProfileService myProfileService;
	
	@GetMapping("")
	public String getProfileView(Model model) {
		String username = extractUsername();
		String role = extractAuthority();
		
		model.addAttribute("profile", myProfileService.getMyProfileDetails(username, role));
		return ok("myProfile", model);
	}

}
