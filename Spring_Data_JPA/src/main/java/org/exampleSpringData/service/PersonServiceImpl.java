package org.exampleSpringData.service;

import org.exampleSpringData.entity.Person;
import org.exampleSpringData.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    public List<Person> getAll(){
        return (List<Person>) personRepository.findAll();
    }
}
