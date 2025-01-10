package com.sathya.springbootrestsecurity_jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.springbootrestsecurity_jwt.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class JwtController {

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		if("admin".equals(username) && "password".equals(password))
		{
			String token= JwtUtil.generateToken(username);
			
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(401)
							 .body("Invald usename and password");
	}
}
