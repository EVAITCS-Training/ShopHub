package com.evaitcsmatt.shophub.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
	
	@GetMapping(value = {"", "/"})
	public String home() {
		return "home";
	}
}
