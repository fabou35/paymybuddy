package com.paymybuddy.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.paymybuddy.model.Transaction;
import com.paymybuddy.paymybuddy.service.AccountService;
import com.paymybuddy.paymybuddy.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/transfer")
	public String transfer(Model model) {
		
		// Connection List
		List<String> connectionPseudos = transactionService.getPseudoConnectionForUser(1);
		model.addAttribute("connections", connectionPseudos);

		// Transaction table
		List<Transaction> transactions = transactionService.getTransactionsForUser(1);
		model.addAttribute("transactions", transactions);
		return "transaction";
	}
}
