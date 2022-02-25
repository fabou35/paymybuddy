package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.repository.BankAccountRepository;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	public Optional<BankAccount> getBankAccountById(int id) {
		return bankAccountRepository.findById(id);
	}
	
	public Iterable<BankAccount> getBankAccounts() {
		return bankAccountRepository.findAll();
	}
	
	public BankAccount saveBankAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}
	
	public void deleteBankAccount(BankAccount bankAccount) {
		bankAccountRepository.delete(bankAccount);
	}
}
