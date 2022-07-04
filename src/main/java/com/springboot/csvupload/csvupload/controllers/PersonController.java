package com.springboot.csvupload.csvupload.controllers;

import com.springboot.csvupload.csvupload.entity.Person;
import com.springboot.csvupload.csvupload.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("persons")
    public Iterable<Person> getAllPersons(){
        Iterable<Person> personIterable = personRepository.findAll();
        return personIterable;
    }

    @RequestMapping(value ="/persons/{id}", method=RequestMethod.GET)
    public Optional<Person> getPerson(@PathVariable("id") Integer id){
        Optional<Person> person = personRepository.findById(id);
        return person;
    }

    @RequestMapping(value ="/persons/color/{color}", method=RequestMethod.GET)
    public Iterable<Person> getPerson(@PathVariable("color") String color){
        Iterable<Person> personIterable = personRepository.findAllByColor(color);
        return personIterable;
    }

}
