package com.paymybuddy.paymybuddy.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity linked to the commission table in the database
 * @author fabien
 *
 */

@Entity
@Table(name = "commission")
public class Commission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commission_id")
	private int commissionId;
	
	@Column(name = "rate")
	private float rate;
	
	@Column(name = "date_modification")
	private Date dateModification;

	public int getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(int commissionId) {
		this.commissionId = commissionId;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	
}
