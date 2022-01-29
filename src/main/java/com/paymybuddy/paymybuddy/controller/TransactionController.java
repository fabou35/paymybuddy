package com.paymybuddy.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

	@GetMapping("/transfer")
	public String transfer() {
		return "transaction";
	}
}
