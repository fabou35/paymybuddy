package com.paymybuddy.paymybuddy.model;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity linked to the transaction table in the database
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

	/**
	 * connection with the table commission in the database
	 * allows to recover all the commission data for a transaction
	 */
	@ManyToOne(
			fetch = FetchType.EAGER,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
			)
	@JoinColumn(
			name = "commission_id",
			insertable = false,
			updatable = false
			)
	private Commission commission;
	
	/**
	 * connection with the table account in the database
	 * allows to recover all the account data for connection account during a transaction
	 */
	@ManyToOne(
			fetch = FetchType.EAGER,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
			)
	@JoinColumn(
			name = "connection_id",
			insertable = false,
			updatable = false
			)
	private Account connection;
	
	/**
	 * connection with the table account in the database
	 * allows to recover all the account data for user account during a transaction
	 */
	@ManyToOne(
			fetch = FetchType.EAGER,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
			)
	@JoinColumn(
			name = "account_id",
			insertable = false,
			updatable = false
			)
	private Account user;
	
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

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commissionRate) {
		this.commission = commissionRate;
	}

	public Account getConnection() {
		return connection;
	}

	public void setConnection(Account connection) {
		this.connection = connection;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}
	
}
