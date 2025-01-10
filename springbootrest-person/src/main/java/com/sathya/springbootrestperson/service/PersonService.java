package com.sathya.springbootrestperson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springbootrestperson.person.Person;
import com.sathya.springbootrestperson.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
	
}
