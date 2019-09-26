package com.sapient.calc.Bean;

import java.io.Serializable;

public class TrasactionResponse  implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String transactionType;
	private String transactionDate;
	private String priority;
	
	private String processingFee;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	
	

	
	

}
