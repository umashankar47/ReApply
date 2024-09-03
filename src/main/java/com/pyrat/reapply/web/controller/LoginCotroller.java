package com.pyrat.reapply.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginCotroller {
	
	
	@GetMapping
	public String getLogin() {
		
		System.out.println("login page");
		
		return "login";
	}
	
	

	

}
