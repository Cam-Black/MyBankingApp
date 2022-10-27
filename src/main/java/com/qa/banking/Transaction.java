package com.qa.banking;

import java.time.LocalDate;

public class Transaction {
	private LocalDate transactionDate;
	private String vendor;
	private Double amount;
	private String category;
	
	public Transaction() {
		super();
	}
	
	public Transaction(LocalDate transactionDate, String vendor, Double amount, String category) {
		super();
		this.transactionDate = transactionDate;
		this.vendor = vendor;
		this.amount = amount;
		this.category = category;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Transaction Date: " + getTransactionDate() + " Vendor: " + getVendor() + " Amount: " + getAmount() +
				" Category: " + getCategory();
	}
}
