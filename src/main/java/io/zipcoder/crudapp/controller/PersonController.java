package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    public List<Person> getPersonList(){
        return new ArrayList<>();
    }

    public Person createPerson(Person person) {
        return person;
    }

    public Person getPersonById(int id) {
        return null;
    }

    public Person updatePerson(Person person) {
        return person;
    }

    public void deletePersonById(int id) {
    }
}
