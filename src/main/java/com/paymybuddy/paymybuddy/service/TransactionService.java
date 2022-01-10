package com.paymybuddy.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Transaction;
import com.paymybuddy.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
	public Optional<Transaction> getTransactionById(int id) {
		return transactionRepository.findById(id);
	}
	
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}
	
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	
	/**
	 * carries out a transaction and saves it in database
	 * @param accountId : account id of the user
	 * @param connectionId : account id of the connection
	 * @param amount : amount of the transaction
	 * @param description : small description of the transaction
	 * @param commissionId : id of the commission of the transaction
	 * @return the Transaction if carried, null else
	 */
	public Transaction payToAFriend(int accountId, int connectionId, Float amount, String description,
			int commissionId) {
		Transaction transaction = new Transaction();
		transaction.setAccountId(accountId);
		transaction.setConnectionId(connectionId);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setCommissionId(commissionId);
		if(accountService.verifyBalance(accountId, amount)) {
			transaction = saveTransaction(transaction);
			accountService.calculateNewBalance(transaction);
			return transaction;
		}
		else {
			System.out.println("Error: the transaction can't carried out");
			System.out.println("Please, add money to the account");
			return null;
		}
		
	}
}
