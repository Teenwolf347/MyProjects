package org.exampleSpringData;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>{

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

    @Query("select u.lastName, u.firstName from Person u where u.id = ?1")
    String person(Integer id);

    @Query("select u from Person u where u.lastName like ?1%")
    List<Person> findByAndSort(String lastName, Sort sort);

    @Query("select u from Person u join fetch u.address where u.id = ?1")
    List<Person> findByIdFetchAddress(Integer id);

    @Query("select u from Person u inner join u.passport where u.id = ?1")
    Person findByIdFetchPassport(Integer id);

    @Query("select u from Person u join fetch u.address join fetch u.passport where u.id = ?1")
    Person findByIdFetchAddressAndPassport(Integer id);

    @Modifying()
    @Transactional
    @Query("update Person u set u.firstName = ?1 where u.id = ?2")
    int setFixedFirstNameFor(String firstName, Integer id);

    @Modifying
    @Transactional
    @Query("delete from Person u where u.firstName = :firstName")
    void deleteByFirstName(@Param("firstName") String firstName);

    @Procedure("Count_1")
    Integer countBy();

}
