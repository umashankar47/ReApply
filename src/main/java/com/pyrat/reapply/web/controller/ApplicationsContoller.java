package com.pyrat.reapply.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pyrat.reapply.service.ApplicationService;

@Controller
@RequestMapping("/applications")
public class ApplicationsContoller {
	

	private final ApplicationService appService;
	
	
	public ApplicationsContoller(ApplicationService appService) {
		
		this.appService = appService;
		
	}
	
	public String listApplications(Model model) {
		
		model.addAttribute("applications", this.appService.getAllApplications());
		
		return "applications";
		
	}

}
