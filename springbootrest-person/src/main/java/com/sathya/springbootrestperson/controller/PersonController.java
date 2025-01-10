package com.sathya.springbootrestperson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.springbootrestperson.person.Person;
import com.sathya.springbootrestperson.service.PersonService;

@RestController

public class PersonController {

	@Autowired
	PersonService personService;
	
	@PostMapping("/saveperson")
	public ResponseEntity<Person> savePerson(@RequestBody Person person)
	{
		 Person person2= personService.savePerson(person);
		 
		 return ResponseEntity.status(HttpStatus.CREATED)
				              .header("info", "data added succefully")
				              .body(person2);
	}
}
