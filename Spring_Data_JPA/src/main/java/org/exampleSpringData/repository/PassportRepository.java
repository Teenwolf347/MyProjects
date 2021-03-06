package org.exampleSpringData.repository;

import org.exampleSpringData.entity.Passport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {

}
