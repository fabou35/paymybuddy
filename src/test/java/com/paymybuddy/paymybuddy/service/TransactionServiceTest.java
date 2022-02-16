package com.paymybuddy.paymybuddy.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Commission;
import com.paymybuddy.paymybuddy.model.Transaction;

@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Test
	public void payToAFriendWithGoodParameters() {
		// GIVEN
		Account accountUser = accountService.getAccountById(1).get();
		
		Account accountConnection = accountService.getAccountById(2).get();
		
		Commission commission = commissionService.getCommissionById(1).get();
		
		Float amount = 10f;
		
		// WHEN
		Transaction transaction = new Transaction();
		transaction = transactionService.payToAFriend(accountUser.getAccountId(), accountConnection.getAccountId(),
				amount, "test", commission.getCommissionId()) ;
		
		float balanceUser = accountUser.getBalance() - amount - amount*commission.getRate();
		accountUser.setBalance(balanceUser);
		float balanceConnection = accountConnection.getBalance() + amount;
		accountConnection.setBalance(balanceConnection);
				
		//THEN
		assertThat(transaction.getTransactionId()).isNotNull();
		assertThat(accountService.getAccountById(accountUser.getAccountId()).get().getBalance())
			.isEqualTo(accountUser.getBalance());
		assertThat(accountService.getAccountById(accountConnection.getAccountId()).get().getBalance())
			.isEqualTo(accountConnection.getBalance());
	}
	
	@Test
	public void payToAFriendWithNegativeAmount() {
		// GIVEN
		Account accountUser = accountService.getAccountById(1).get();
		
		Account accountConnection = accountService.getAccountById(2).get();
		
		Commission commission = commissionService.getCommissionById(1).get();
		
		Float amount = -10f;
		
		// WHEN
		Transaction transaction = new Transaction();
		transaction = transactionService.payToAFriend(accountUser.getAccountId(), accountConnection.getAccountId(),
				amount, "test", commission.getCommissionId());
		
		// THEN
		assertThat(transaction).isNull();
	}
}
