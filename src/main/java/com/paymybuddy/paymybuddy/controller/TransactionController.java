package com.paymybuddy.paymybuddy.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.paymybuddy.model.Transaction;
import com.paymybuddy.paymybuddy.service.AccountService;
import com.paymybuddy.paymybuddy.service.PersonService;
import com.paymybuddy.paymybuddy.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/transfer")
	public String transfer(Model model) {
		
		int accountId = personService.getLogedUserId();
		
		// Connection List
		List<String> connectionPseudos = transactionService.getPseudoConnectionForUser(accountId);
		model.addAttribute("connections", connectionPseudos);

		// user balance
		float balance = personService.getPersonById(accountId).get().getAccountId().getBalance();
		model.addAttribute("balance", balance);
		
		// Transaction table
		List<Transaction> transactions = transactionService.getTransactionsForUser(accountId);
		model.addAttribute("transactions", transactions);
		return "transaction";
	}
	
	@PostMapping("/transfer")
	public String saveTransaction(@RequestParam String pseudo, 
			@ModelAttribute Transaction newTransaction, Model model) {
		
		newTransaction.setConnectionId(transactionService.findByPseudoJPQL(pseudo).getAccountId());
		model.addAttribute("newTransaction", newTransaction);
		return "description";
	}
	
	@PostMapping("/description")
	public ModelAndView saveDescription(@ModelAttribute Transaction newTransaction, Model model,
			float amount, int connectionId) {
		
		model.addAttribute("newTransaction", newTransaction);
		transactionService.payToAFriend(personService.getLogedUserId(), connectionId, amount, newTransaction.getDescription(), 1);
		return new ModelAndView("redirect:/transfer");
	}
}
