package com.paymybuddy.paymybuddy.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Commission;
import com.paymybuddy.paymybuddy.model.Transaction;

@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Test
	public void calculateNewBalanceModifiesBalances() {
		
		// GIVEN
		Account accountUser = new Account();
		accountUser.setAccountId(1);
		accountUser.setBalance(100);
		accountUser.setPseudo("Fabien");
		accountService.saveAccount(accountUser);
		
		Account accountConnection = new Account();
		accountConnection.setAccountId(2);
		accountConnection.setBalance(200);
		accountConnection.setPseudo("Hayley");
		accountService.saveAccount(accountConnection);

		Commission commission = commissionService.getCommissionById(1).get();
		
		float amount = 10;
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(1);
		transaction.setConnectionId(2);
		transaction.setAmount(amount);
		transaction.setDescription("test");
		transaction.setCommissionId(1);
		
		float balanceUser = accountUser.getBalance() - amount - amount*commission.getRate();
		float balanceConnection = accountConnection.getBalance() + amount;
		
		// WHEN
		accountService.calculateNewBalance(transaction);
		
		// THEN
		assertThat(accountService.getAccountById(1).get().getBalance()).isEqualTo(balanceUser);
		assertThat(accountService.getAccountById(2).get().getBalance()).isEqualTo(balanceConnection);
		
	}
}
