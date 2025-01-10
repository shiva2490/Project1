package com.sathya.springbootrestsecurity_sso1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/greet")
	public String wish()
	{
		return "Welcome to Spring Boot Security";
	}
}
