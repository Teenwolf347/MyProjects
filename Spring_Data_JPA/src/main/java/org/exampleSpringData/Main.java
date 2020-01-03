package org.exampleSpringData;


import org.exampleSpringData.repository.PersonRepository;
import org.exampleSpringData.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.exampleSpringData.config")
@Transactional
public class Main  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;
    public void run(String... args) throws Exception {

        String[] s = personRepository.findByIdAll();
        System.out.println(Arrays.toString(s));


        // Добавление данныхв таблицу через коллекцию
//        Person person_1 = new Person("Bob","Smite");
//        Person person_2 = new Person("Ann","Smite");
//        Person person_3 = new Person("Jim","Grey");
//
//        Passport passport_1 = new Passport(4515,355354);
//        Passport passport_2 = new Passport(4512,859632);
//        Passport passport_3 = new Passport(4516,555344);
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
//
//        List<Person> list = Arrays.asList(person_1, person_2, person_3);
//        personRepository.saveAll(list);

        // Добавление данных по ожной сущности
//        Person person_4 = new Person("Key","Greys");
//        Address address_3 = new Address("London","St1",5);
//        Passport passport_4 = new Passport(5555,367352);
//        person_4.setAddress(address_3);
//        person_4.setPassport(passport_4);
//        personRepository.save(person_4);


//
//        System.out.println(personRepository.findAll());

//
////        for (String value : s) {
////            for (String s1 : value.split(",")) {
////                System.out.println(s1);
////            }
////        }

    }
}
