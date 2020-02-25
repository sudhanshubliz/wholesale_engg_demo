/**
 * 
 */
package com.wholesale.demo.model;

/**
 * @author sudthaku
 *
 */
 
public class AccountTransactionDTO {

	 private String accountNumber;

	 private String accountName;
	 private String valueDate;
	 private String currency;
	 private Double debitAmount;
	 private Double creditAmount;

	 private AccountCreditDebit accountCreditDebit;
	 private String TransactionNarrative;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public AccountCreditDebit getAccountCreditDebit() {
		return accountCreditDebit;
	}
	public void setAccountCreditDebit(AccountCreditDebit accountCreditDebit) {
		this.accountCreditDebit = accountCreditDebit;
	}
	public String getTransactionNarrative() {
		return TransactionNarrative;
	}
	public void setTransactionNarrative(String transactionNarrative) {
		TransactionNarrative = transactionNarrative;
	}
	 

	
}
