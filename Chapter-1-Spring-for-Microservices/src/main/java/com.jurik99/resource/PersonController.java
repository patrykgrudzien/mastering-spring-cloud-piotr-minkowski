package com.jurik99.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.model.Person;
import com.jurik99.repository.PersonRepository;
import com.jurik99.service.PersonCounterService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private List<Person> people = new ArrayList<>();

    private final PersonCounterService personCounterService;

    private final PersonRepository personRepository;

    public PersonController(final PersonCounterService personCounterService,
                            final PersonRepository personRepository) {
        this.personCounterService = personCounterService;
	    this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") final String id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Exception!"));
    }

	@GetMapping("/lastName/{lastName}")
	public List<Person> findByLastName(@PathVariable final String lastName) {
    	return personRepository.findByLastName(lastName);
	}

	@GetMapping("/age/{age}")
	public List<Person> findByAgeGreaterThan(@PathVariable final int age) {
    	return personRepository.findByAgeGreaterThan(age);
	}

    @PostMapping
    public Person add(@RequestBody Person person) {
    	person = personRepository.save(person);
        personCounterService.countNewPersons();
        return person;
    }

    @PutMapping
    public void update(@RequestBody final Person p) {
        final Person person = people.stream()
                                    .filter(it -> it.getId().equals(p.getId()))
                                    .findFirst()
                                    .orElseThrow(RuntimeException::new);
        people.set(people.indexOf(person), p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
    	personRepository.deleteById(id);
        personCounterService.countDeletedPersons();
    }
}
