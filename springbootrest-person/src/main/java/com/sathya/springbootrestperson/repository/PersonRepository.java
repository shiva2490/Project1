package com.sathya.springbootrestperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.springbootrestperson.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
