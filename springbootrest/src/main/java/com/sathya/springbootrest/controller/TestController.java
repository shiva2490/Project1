package com.sathya.springbootrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	@GetMapping("/pincodedetails/{pincode}")
	public ResponseEntity<String> getPincodeDetails(@PathVariable("pincode") String pincode)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://api.postalpincode.in/pincode/"+pincode, String.class);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .header("Status", "Data read sucefully")
							 .body(response.getBody());
	}
	
	@GetMapping("/quotes/{id}")
	public ResponseEntity<String> getQuotesDetails(@PathVariable("id") String id)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://dummyjson.com/quotes/"+id, String.class);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .header("Status", "Data read sucefully")
							 .body(response.getBody());
	}

	
	@GetMapping("/recipes/{id}")
	public ResponseEntity<String> getRecipesDetails(@PathVariable("id") String id)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://dummyjson.com/recipes/"+id, String.class);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .header("Status", "Data read sucefully")
							 .body(response.getBody());
	}
	
	
	
	
	@GetMapping("/getweather")
	public ResponseEntity<String> getWeatherDetails()  
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?lat=14.552200&lon=77.111000&appid=908c6b8bf279abbf10d80c063a2113c7", String.class);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .header("info", "Data read succefully")
							 .body(response.getBody());
	}
}
