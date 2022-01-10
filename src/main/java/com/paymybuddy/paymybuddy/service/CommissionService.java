package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Commission;
import com.paymybuddy.paymybuddy.repository.CommissionRepository;

@Service
public class CommissionService {

	@Autowired
	private CommissionRepository commissionRepository;
	
	public Optional<Commission> getCommissionById(int id) {
		return commissionRepository.findById(id);
	}
	
	public Iterable<Commission> getCommissions() {
		return commissionRepository.findAll();
	}
	
	public Commission saveCommission(Commission commission) {
		return commissionRepository.save(commission);
	}
	
	public void deleteCommission(Commission commission) {
		commissionRepository.delete(commission);
	}
	
	/**
	 * calculates the amount of the commission for a commission rate
	 * @param amount : transaction amount 
	 * @param commissionRate : commission rate for the transaction
	 * @return the commission amount
	 */
	public float calculateCommissionAmount(float amount, float commissionRate) {
		return amount * commissionRate;
	}
}
