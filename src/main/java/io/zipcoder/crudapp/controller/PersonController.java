package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.entity.Person;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping //get the list of all people
    public ResponseEntity<List<Person>> getPersonList() {
        List<Person> people = (List<Person>) personRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping // create a new person
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //get the person with the id number
    public ResponseEntity<Person> getPerson(@PathVariable Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}") //update the person with id number
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    existingPerson.setFirstName(person.getFirstName());
                    existingPerson.setLastName(person.getLastName());
                    Person updatedPerson = personRepository.save(existingPerson);
                    return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
                }).orElseGet(() -> {
                    person.setId(id);
                    Person newPerson = personRepository.save(person);
                    return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
                });
    }

    @DeleteMapping("/{id}") //de;ete the person with id number
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        personRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
