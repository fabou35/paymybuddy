package com.paymybuddy.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
}
