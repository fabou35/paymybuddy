package com.paymybuddy.paymybuddy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	@Query("FROM Account WHERE pseudo=?1")
	public Account findByPseudoJPQL(String pseudo);
}
