package com.sapient.calc.Bean;

import java.io.Serializable;

public class TrasactionDto  implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String externalTrasaction;
	private String clientId;
	private String securityId;
	private String transactionType;
	private String transactionDate;
	private String marketValue;
	private String privateFlag;
	
	
	private String processingFee;
	
	
	public String getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	public String getExternalTrasaction() {
		return externalTrasaction;
	}
	public void setExternalTrasaction(String externalTrasaction) {
		this.externalTrasaction = externalTrasaction;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
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
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getPrivateFlag() {
		return privateFlag;
	}
	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}
	

	
	
	

}
