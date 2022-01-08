package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Optional<Account> getAccountById(int id) {
		return accountRepository.findById(id);
	}
	
	public Iterable<Account> getAccounts(){
		return accountRepository.findAll();
	}
	
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}
	
	public void deleteAccount(Account account) {
		accountRepository.delete(account);
	}
}
