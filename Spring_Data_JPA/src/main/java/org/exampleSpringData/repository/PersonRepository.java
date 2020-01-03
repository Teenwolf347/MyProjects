package org.exampleSpringData.repository;

import org.exampleSpringData.entity.Address;
import org.exampleSpringData.entity.Passport;
import org.exampleSpringData.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findByFirstName(String firstName);

    List<Person> findByAddress(Address address);

    List<Person> findByFirstNameAndAddress(String firstName, Address address);

    List<Person> findByFirstNameOrAddress(String firstName, Address address);

    List<Person> findByIdBetween(Integer id_1, Integer id_2);

    List<Person> findByIdLessThan(Integer id);

    List<Person> findByIdLessThanEqual(Integer id);

    List<Person> findByIdGreaterThan(Integer id);

    List<Person> findByIdGreaterThanEqual(Integer id);

    List<Person> findByAddressIsNull();

    List<Person> findByAddressIsNotNull();

    List<Person> findByFirstNameLike(String text);

    List<Person> findByFirstNameNotLike(String text);

    List<Person> findByFirstNameStartingWith(String text);

    List<Person> findByFirstNameEndingWith(String text);

    List<Person> findByFirstNameContaining(String text);

    List<Person> findByAddressOrderByLastName(Address address);

    List<Person> findByAddressOrderByLastNameDesc(Address address);

    List<Person> findByFirstNameNot(String firstName);

    List<Person> findByLastNameIn(Collection<String> lastName);

    List<Person> findByLastNameNotIn(Collection<String> lastName);

    List<Person> findByAddressTrue();

    List<Person> findByAddressFalse();

    List<Person> findByFirstNameIgnoreCase(String firstName);

    void deleteById(Integer id);

    @Query("select u from Person u where u.lastName like ?1%")
    List<Person> findByAndSort(String lastName, Sort sort);

    @Query("select u.firstName, u.lastName from Person u join u.address where u.id = ?1")
    String[] findByIdFetchAddress(Integer id);

    @Query("select u from Person u join u.passport where u.id = ?1")
    Person findByIdFetchPassport(Integer id);

    @Query("select u from Person u join u.address join u.passport where u.id = ?1")
    Person findByIdFetchAddressAndPassport(Integer id);

    @Modifying
    @Query("update Person u set u.passport.series = ?1 where u.id = ?2")
    int setFixedFirstNameFor(Integer series, Integer id);

    @Modifying
    @Query("delete from Person u where u.firstName = :firstName")
    void deleteByFirstName(@Param("firstName") String firstName);

    @Procedure("Count_1")
    Integer countBy();

    @Query("select u.firstName, u.lastName from Person u ")
    String[] findByIdAll();

}
