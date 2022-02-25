package com.paymybuddy.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity linked to person table in the database 
 * @author fabien
 *
 */
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pass")
	private String pass;
	
	/**
	 * Bidirectionality connection with Account entity
	 * allows to recover the account data for a person
	 */
	@OneToOne(
			mappedBy = "personId"
			)
	private Account accountId;
	
	/**
	 * Entity linked to the connection_association link table in the database
	 * allows to recover all the connections data for a person
	 */
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}			
			)
	@JoinTable(
			name = "connection_association",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "connection_id")
			)
	private List<Person> connections = new ArrayList<>();

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public List<Person> getConnections() {
		return connections;
	}

	public void setConnections(List<Person> connections) {
		this.connections = connections;
	}

	
}
