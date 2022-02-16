package com.paymybuddy.paymybuddy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Person;
import com.paymybuddy.paymybuddy.model.Transaction;
import com.paymybuddy.paymybuddy.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PersonService personService;
	
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
	
	public Account findByPseudoJPQL(String pseudo) {
		return transactionRepository.findByPseudoJPQL(pseudo);
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
	public Transaction payToAFriend(int accountId, int connectionId, float amount, String description,
			int commissionId) {
		
		
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(accountId);
		transaction.setConnectionId(connectionId);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setCommissionId(commissionId);
		
		if(amount > 0) {
			if(accountService.verifyBalance(accountId, amount)) {
				transaction = saveTransaction(transaction);
				accountService.calculateNewBalance(transaction);
				System.out.println("Transaction carried out");
				return transaction;
			}
			else {
				System.out.println("Error: transaction not carried out");
				System.out.println("Insufficient balance");
				return null;
			}
		}
		else {
			System.out.println("Error: transaction not carried out");
			System.out.println("Negative amount");
			return null;
		}
	}
	
	/**
	 * retrieves the connections for an user
	 * 
	 * @param accountId : user account id
	 * @return Person pseudo List
	 */
	public List<String> getPseudoConnectionForUser(int accountId) {
		Optional<Person> optionalUser = personService.getPersonById(accountService.getAccountById(accountId).get().getPersonId().getPersonId());
		Person user = optionalUser.get();
		Iterable<Person> connections = user.getConnections();
		List<String> connectionPseudos = new ArrayList<>();
		connections.forEach(connection -> connectionPseudos.add(connection.getAccountId().getPseudo()));
		return connectionPseudos;
	}

	/**
	 * retrieves the first three transactions for an user
	 * 
	 * @param accountId : user account id
	 * @return Transaction List
	 */
	public List<Transaction> getTransactionsForUser(int accountId) {
		List<Transaction> transactions = new ArrayList<>();
		Iterable<Transaction> allTransactions = getTransactions();
		List<Transaction> userTransactions = new ArrayList<>();
		for (Transaction transaction : allTransactions) {
			if (transaction.getAccountId() == accountId) {
				transactions.add(transaction);
			}
		}
		Collections.reverse(transactions);
		if (transactions.size() >= 3) {
			for (int i = 0; i < 3; i++) {
				userTransactions.add(transactions.get(i));
			}
		}
		else {
			for (int i = 0; i < transactions.size(); i++) {
				userTransactions.add(transactions.get(i));
			}
		}
		return userTransactions;
	}
}
