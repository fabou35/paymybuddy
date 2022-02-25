package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Commission;
import com.paymybuddy.paymybuddy.model.Transaction;
import com.paymybuddy.paymybuddy.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CommissionService commissionService;

	public Optional<Account> getAccountById(int id) {
		return accountRepository.findById(id);
	}

	public Iterable<Account> getAccounts() {
		return accountRepository.findAll();
	}

	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	public void deleteAccount(Account account) {
		accountRepository.delete(account);
	}

	/**
	 * verifies a transaction can be carried out
	 * 
	 * @param accountId : Id of the user account
	 * @param amount    : amount which must be subtracted from the user account
	 *                  balance
	 * @return true if the balance is greater than or equal to the amount of the
	 *         transaction
	 */
	public boolean verifyBalance(int accountId, float amount) {
		Optional<Account> accountOptional = getAccountById(accountId);
		Account account = accountOptional.get();
		float balance = account.getBalance();
		if (balance >= amount) {
			log.info("User balance sufficient for the transaction");
			return true;
		}
		log.info("User balance insufficient for the transaction");
		return false;
	}

	/**
	 * subtracts the amount from the user account and adds the amount to the
	 * connection account
	 * 
	 * @param transaction : Transaction carried out between an user and a connection
	 */
	@Transactional
	public void calculateNewBalance(Transaction transaction) {
		Optional<Account> userAccountOptional = getAccountById(transaction.getAccountId());
		Account userAccount = userAccountOptional.get();
		Optional<Account> connectionAccountOptional = getAccountById(transaction.getConnectionId());
		Account connectionAccount = connectionAccountOptional.get();
		Optional<Commission> commissionOptional = commissionService.getCommissionById(transaction.getCommissionId());
		Commission commission = commissionOptional.get();
		float commissionRate = commission.getRate();

		float commissionAmount = commissionService.calculateCommissionAmount(transaction.getAmount(),
				commissionRate);
		float userbalance = userAccount.getBalance() - transaction.getAmount() - commissionAmount;
		userAccount.setBalance(userbalance);
		log.info("User balance recalculated");
		userAccount = saveAccount(userAccount);

		float connectionBalance = connectionAccount.getBalance() + transaction.getAmount();
		connectionAccount.setBalance(connectionBalance);
		log.info("Connection balance recalculated");
		saveAccount(connectionAccount);
	}
}
