package com.coding.domain;

import java.util.Date;


public class Payer {
	
	private String payerName;
	private int points;
	private String transactionDate;
	
	
	public Payer() {}
	
	public Payer(String payerName, int points, String transactionDate) {
		super();
		this.payerName = payerName;
		this.points = points;
		this.transactionDate = transactionDate;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Payer [payerName=" + payerName + ", points=" + points + ", transactionDate=" + transactionDate + "]";
	}
	
	
	
	
	

}
