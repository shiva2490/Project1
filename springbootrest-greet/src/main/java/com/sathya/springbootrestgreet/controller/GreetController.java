package com.sathya.springbootrestgreet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
	
	@GetMapping("/greet")
	public String greet()
	{
		return "Hi Ratan sir";
	}

}
