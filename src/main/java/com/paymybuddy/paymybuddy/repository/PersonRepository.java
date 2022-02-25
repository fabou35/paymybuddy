package com.paymybuddy.paymybuddy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{

	@Query("FROM Person WHERE email=?1")
	public Person findByEmailJPQL(String email);
}
