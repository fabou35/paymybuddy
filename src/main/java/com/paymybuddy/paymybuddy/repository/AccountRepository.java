package com.paymybuddy.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

}
