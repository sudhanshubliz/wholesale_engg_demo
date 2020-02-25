package com.wholesale.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.wholesale.demo.model.Account;
import com.wholesale.demo.model.AccountTransactionDTO;

public class AccountRepository {

	private List<Account> accounts = new ArrayList<>();
	
	public Account add(Account account) {
 		accounts.add(account);
		return account;
	} 
	
	public List<Account> findAll() {
		return accounts;
	}
	
	public List<AccountTransactionDTO> findByAccountTransaction(String accountId) {
		AccountTransactionDTO transactionDTO = new AccountTransactionDTO();
		List<AccountTransactionDTO> transactionList = new ArrayList<AccountTransactionDTO>();

		IntStream.range(0, accounts.size()).forEachOrdered(index -> {

			if(accounts.get(index).getAccountNumber().equals(accountId)) {
				transactionDTO.setAccountNumber(accounts.get(index).getAccountNumber().
						substring(0, 3)+"-"+accounts.get(index).getAccountNumber().substring(3, 7)+"-"+
						accounts.get(index).getAccountNumber().substring(7, 10));
				transactionDTO.setAccountName(accounts.get(index).getAccountName());
				transactionDTO.setValueDate(accounts.get(index).getValueDate());
				transactionDTO.setCurrency(accounts.get(index).getCurrency());
				transactionDTO.setDebitAmount(accounts.get(index).getDebitAmount());
				transactionDTO.setCreditAmount(accounts.get(index).getCreditAmount());
				transactionDTO.setAccountCreditDebit(accounts.get(index).getAccountCreditDebit());
				transactionDTO.setTransactionNarrative(accounts.get(index).getTransactionNarrative());
				transactionList.add(transactionDTO);
			}
		
		});
		  
			return transactionList;      
		
 				  
				 
	}
	 
}
