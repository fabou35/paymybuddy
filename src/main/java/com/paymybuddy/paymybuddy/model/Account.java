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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity linked to the account table in the database
 * @author fabien
 *
 */
@Entity
@DynamicUpdate
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int accountId;
	
	@Column(name = "pseudo")
	private String pseudo;
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "balance")
	private float balance;
	
	@Column(name = "account_status")
	private boolean accountStatus;
	
	/**
	 * connection with the table bank_account in the database
	 * allows to recover the bank account data for an account
	 */
	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;
	
	/**
	 * connection with the table person in the database
	 * allows to recover the person data for an account
	 */
	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	@JoinColumn(name = "person_id")
	private Person personId;
	
	/**
	 * bidirectional connection with the table transaction in the database
	 * allows to recover all the transactions data for an account
	 */	
	@OneToMany(
			mappedBy = "user",
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			fetch = FetchType.LAZY
			)
	private List<Transaction> transactions = new ArrayList<>();
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
		this.personId = personId;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
