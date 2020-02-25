/**
 * 
 */
package com.wholesale.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author sudthaku
 *
 */


@Entity
@Data
public class Account {

	@Id
	 private String accountNumber;
	 private String accountName;
	 private AccountType accountType;
	 private String balancedDate;
	 private String currency;
	 private Double openingAvailBalance;
	 private String valueDate;
	 private Double debitAmount;

	 private Double creditAmount;

	 
	 private AccountCreditDebit accountCreditDebit;
	 private String TransactionNarrative;
	 
	public Account(String accountNumber, String accountName, AccountType accountType, String balancedDate,
			String currency, Double openingAvailBalance, String valueDate, Double creditAmount,
			AccountCreditDebit accountCreditDebit, String transactionNarrative) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountType = accountType;
		this.balancedDate = balancedDate;
		this.currency = currency;
		this.openingAvailBalance = openingAvailBalance;
		this.valueDate = valueDate;
		this.creditAmount = creditAmount;
		this.accountCreditDebit = accountCreditDebit;
		TransactionNarrative = transactionNarrative;
	}

	 
}
