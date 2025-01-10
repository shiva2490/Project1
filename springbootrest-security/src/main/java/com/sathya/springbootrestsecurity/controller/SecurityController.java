package com.sathya.springbootrestsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/welcome")
	public String info()
	{
		return "Welcome to Spring Boot Security";
	}
	

}
