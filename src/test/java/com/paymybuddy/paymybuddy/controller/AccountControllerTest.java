package com.paymybuddy.paymybuddy.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void testLoginPage() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testLoginPageAuthentication() throws Exception {
		mockMvc.perform(formLogin().user("fabien@email.com").password("1234"))
			.andExpect(redirectedUrl("/transfer"));
	}
}
