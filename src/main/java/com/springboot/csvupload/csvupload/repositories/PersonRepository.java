package com.springboot.csvupload.csvupload.repositories;

import com.springboot.csvupload.csvupload.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Iterable<Person> findAllByColor(String color);

}
