package com.nasa.asteral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@GetMapping("")
	public String getLoginView(Model model) {
		return ok("login", model);
	}

}
