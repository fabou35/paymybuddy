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
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int transactionId;
	
	@Column(name = "account_id")
	private int accountId;
	
	@Column(name = "connection_id")
	private int connectionId;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "commission_id")
	private int commissionId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(int commissionId) {
		this.commissionId = commissionId;
	}

	
}
