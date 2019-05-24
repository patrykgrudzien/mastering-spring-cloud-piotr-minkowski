package com.jurik99.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jurik99.model.Person;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByLastName(String lastName);

	List<Person> findByAgeGreaterThan(int age);
}
