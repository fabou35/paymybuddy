package com.paymybuddy.paymybuddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.paymybuddy.model.Transaction;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testTransactionPageNeedsAuthentication() throws Exception {
		mockMvc.perform(get("/transfer"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithMockUser(username = "fabien@email.com", password = "1234")
	public void testAuthentication() throws Exception {
		mockMvc.perform(get("/transfer"))
			.andExpect(status().isOk())
			.andExpect(view().name("transaction"));	
	}
	
	@Test
	@WithMockUser(username = "fabien@email.com", password = "1234")
	public void test() throws Exception{
		Transaction transaction = new Transaction();
		transaction.setAccountId(1);
		transaction.setAmount(10);
		transaction.setConnectionId(2);
		
		mockMvc.perform(post("/transfer").param("pseudo", "Hayley"))
			.andExpect(status().isOk());
	}
}
