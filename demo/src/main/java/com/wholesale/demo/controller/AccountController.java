package com.wholesale.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wholesale.demo.model.Account;
import com.wholesale.demo.model.AccountTransactionDTO;
import com.wholesale.demo.repository.AccountRepository;

@RestController
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountRepository repository;
	
	@PostMapping("/")
	public Account add(@RequestBody Account account) {
		LOGGER.info("Account add: {}", account);
		return repository.add(account);
	}
	 
	@GetMapping("/")
	public List<Account> findAll() {
		LOGGER.info("list of account find");
		return repository.findAll();
	}
	
	@GetMapping("/account/{accountNumber}")
	public List<AccountTransactionDTO> findByAccountTransaction(@PathVariable("accountNumber") String accountNumber) {
		LOGGER.info("Account find: accountNumber={}", accountNumber);
		return repository.findByAccountTransaction(accountNumber);
	}
	 
}
