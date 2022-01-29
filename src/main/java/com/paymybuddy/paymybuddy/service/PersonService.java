package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Person;
import com.paymybuddy.paymybuddy.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private HttpSession httpSession;
	
	public Optional<Person> getPersonById(int id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}
	
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
	
	public void deletePerson(Person person) {
		personRepository.delete(person);
	}
	
	public Person findByEmailJPQL(String email) {
		return personRepository.findByEmailJPQL(email);
	}
	
	public int getLogedUserId() {
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		Person userAccount = findByEmailJPQL(securityContext.getAuthentication().getName());
		return userAccount.getAccountId().getAccountId();
	}
	
}
