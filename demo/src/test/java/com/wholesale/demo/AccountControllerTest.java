package com.wholesale.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.wholesale.demo.controller.AccountController;
import com.wholesale.demo.model.Account;
import com.wholesale.demo.model.AccountCreditDebit;
import com.wholesale.demo.model.AccountTransactionDTO;
import com.wholesale.demo.model.AccountType;
import com.wholesale.demo.repository.AccountRepository;
 
@SpringBootTest
public class AccountControllerTest {
  
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountController accountController;
	
	
	@Before(value = "")
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

 	@Test
	public void testFindAccountList() throws IOException {

		List<Account> accountList = new ArrayList<>();

		accountList.add(new Account("7853092009", "SavingAccount", AccountType.Saving, "10/11/2018", "SGD", 57234.55,
				"Jan,12,2012", 54845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		accountList.add(new Account("7853092009", "SavingAccount", AccountType.Saving, "09/11/2018", "SGD", 886234.55,
				"Jan,13,2012", 467845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		accountList.add(new Account("2853092009", "SavingAccount", AccountType.Saving, "11/11/2018", "SGD", 23334.55,
				"Jan,12,2012", 677845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		accountList.add(new Account("3853092009", "SavingAccount", AccountType.Saving, "12/11/2018", "SGD", 55234.55,
				"Jan,15,2012", 64845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		accountList.add(new Account("3853092000", "SavingAccount", AccountType.Saving, "23/11/2018", "SGD", 664234.55,
				"Jan,12,2012", 364845.45, AccountCreditDebit.Credit, "transaction Narrate"));

		when(accountRepository.findAll()).thenReturn(accountList);

		List<Account> accountData = accountController.findAll();

		assertThat(accountData).isNotNull();

	}
 	 
 	@Test
	public void testViewTransactionList() throws IOException {

		List<AccountTransactionDTO> accountTransList = new ArrayList<>();

		AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
		accountTransactionDTO.setAccountNumber("3853092009");
		accountTransactionDTO.setAccountName("SavingAccount");
		accountTransactionDTO.setCreditAmount(364845.45);
		accountTransactionDTO.setCurrency("SGD");
		accountTransactionDTO.setAccountCreditDebit(AccountCreditDebit.Credit);
		accountTransactionDTO.setDebitAmount(38444.90);
		accountTransactionDTO.setValueDate("Jan,12,2018");
		accountTransactionDTO.setTransactionNarrative("TransActive");

		accountTransList.add(accountTransactionDTO);

		when(accountRepository.findByAccountTransaction("3853092009")).thenReturn(accountTransList);

		String accountNo = "3853092009";
		List<AccountTransactionDTO> accountData = accountController.findByAccountTransaction(accountNo);

		assertThat(accountData).isNotNull();

	}
 	 
	 
}
