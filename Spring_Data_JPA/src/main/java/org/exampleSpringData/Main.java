package org.exampleSpringData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    PersonRepository personRepository;

    public void run(String... args) throws Exception {

//        Person person_1 = new Person("Bob","Smite");
//        Person person_2 = new Person("Ann","Smite");
//        Person person_3 = new Person("Jim","Grey");
//
//        Passport passport_1 = new Passport(4515,355354);
//        Passport passport_2 = new Passport(4512,859632);
//        Passport passport_3 = new Passport(4517,555344);
//
//        Address address_1 = new Address("Moscow","Annino", 152);
//        Address address_2 = new Address("Moscow","Dybrovka", 23);
//
//        person_1.setAddress(address_1);
//        person_2.setAddress(address_1);
//        person_3.setAddress(address_2);
//
//        person_1.setPassport(passport_1);
//        person_2.setPassport(passport_2);
//        person_3.setPassport(passport_3);
//
//        List<Person> list = Arrays.asList(person_1, person_2, person_3);
//        personRepository.saveAll(list);

        System.out.println(personRepository.person(3));


    }
}
