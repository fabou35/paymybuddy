package com.paymybuddy.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity linked to the bank_account table in the database
 * @author fabien
 *
 */
@Entity
@Table(name = "bank_account")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_account_id")
	private int bankAccountId;
	
	@Column(name = "iban")
	private String iban;
	
	@Column(name = "bic")
	private String bic;
	
	/**
	 * bidirectional connection with the table account in the database
	 * allows to recover all the account data for bank account
	 */
	@OneToOne(
			mappedBy = "bankAccount"
			)
	private Account account;

	public int getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
