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
import com.jurik99.service.PersonCounterService;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/person")
public class PersonController {

    private List<Person> people = new ArrayList<>();

    private final PersonCounterService personCounterService;

    public PersonController(final PersonCounterService personCounterService) {
        this.personCounterService = personCounterService;
    }

    @GetMapping
    public List<Person> findAll() {
        return people;
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") final Long id) {
        return people.stream()
                     .filter(p -> p.getId().equals(id))
                     .findFirst()
                     .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public Person add(@RequestBody final Person person) {
        person.setId((long) (people.size() + 1));
        people.add(person);
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
    public void delete(@PathVariable final Long id) {
        final List<Person> personList = people.stream()
                                              .filter(it -> it.getId().equals(id))
                                              .collect(toList());
        people.removeAll(personList);
        personCounterService.countDeletedPersons();
    }
}
