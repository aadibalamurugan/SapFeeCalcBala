package com.sapient.calc.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sapient.calc.Bean.TrasactionDto;
import com.sapient.calc.Bean.TrasactionResponse;

@Service
public class FeeCalcServiceImpl implements  FeeCalcService {
    
	/*Created the file in the local driver .*/
	final static String outputfile="F:\\sampleoutput.csv";
	 FileWriter filewriter =null;
	
	 @Override
	public void gettransaction(List<TrasactionDto> trasactionData)
	{
		System.out.println("getransaction start's ...");
		//normal transaction
		   List<TrasactionDto> normalTransactionsData = checkNormalTransaction(trasactionData);
			System.out.println("NormalTransactionsData  Array Size ..." + normalTransactionsData.size());
			
			List<TrasactionDto> intraDayTransaction = checkIntraDayTransaction(trasactionData);
			intraDayTransaction.stream().forEach(u -> u.setProcessingFee("10"));
			System.out.println("IntraDayTransaction  Array Size ..." + intraDayTransaction.size());
			List<TrasactionResponse> transactionResponse = new  ArrayList<TrasactionResponse>();
			for(TrasactionDto transDto : normalTransactionsData) {
				transactionResponse.add(transactionDTO(transDto));
			}
			try {
				 filewriter = new FileWriter(outputfile);
				filewriter.append("clientId,transactionType,transactionDate,priorityType,processFee".toString());
				filewriter.append("\n");
				for(TrasactionResponse rs: transactionResponse) {
					filewriter.append(rs.getClientId());
		            filewriter.append(",");
		            filewriter.append(rs.getTransactionType());
		            filewriter.append(",");
		            filewriter.append(rs.getTransactionDate());
		            filewriter.append(",");
		            filewriter.append(rs.getPriority());
		            filewriter.append(",");
		            filewriter.append(rs.getProcessingFee());
	               filewriter.append("\n");
				}
	            System.out.println("CSV File Generated SuccessFully..");
 			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				 try {
					 // if u didnt close or flush it will auto close will be done in java7.
					filewriter.flush();
			         filewriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}

         }
		
	
	/*  1.checkNormalTransaction used to apply the processingFees based on the priorityFlag + transactionType.
	 *  2. appliedCharagesToMarketValue  is applied the processing fee with the market value (not sure it covered in the requirement)*/
	 
	public List<TrasactionDto> checkNormalTransaction( List<TrasactionDto> transactionsData) {
		String chargesApplied ="";
		List<TrasactionDto> transactionDto = new ArrayList<TrasactionDto>();
		for(TrasactionDto singleTransaction  : transactionsData) {
			//high priority
			if(singleTransaction.getPrivateFlag().equalsIgnoreCase("Y")) {
				 chargesApplied = appliedChargesToMarketValue(singleTransaction.getMarketValue(),500);
				singleTransaction.setMarketValue(chargesApplied);
				singleTransaction.setProcessingFee("500");
			}
			
			else if(singleTransaction.getPrivateFlag().equalsIgnoreCase("N") && singleTransaction.getTransactionType().equalsIgnoreCase("SELL") ||  singleTransaction.getTransactionType().equalsIgnoreCase("WITHDRAW") ) {
				 chargesApplied = appliedChargesToMarketValue(singleTransaction.getMarketValue(),100);
				singleTransaction.setMarketValue(chargesApplied);
				singleTransaction.setProcessingFee("100");

			}
			else {
				 chargesApplied = appliedChargesToMarketValue(singleTransaction.getMarketValue(),50);
				singleTransaction.setMarketValue(chargesApplied);
				singleTransaction.setProcessingFee("50");

			}
			transactionDto.add(singleTransaction);   //updated the amount 
		}
		return transactionDto;
			
	}
	
	public String appliedChargesToMarketValue(String marketValue,int processFee) {
		String x = marketValue.trim();
		try {
		int i =Integer.parseInt(x);
	    int finalvalue = processFee - i;
	     return String.valueOf(finalvalue);
		}
		catch(NumberFormatException e) {
		try {
			double d = Double.parseDouble(x);
		    double finalvalue = processFee - d;
           return String.valueOf(finalvalue);
           }
		catch(NumberFormatException ex) {
			System.out.println("Not a Double or Int");
		}
		return marketValue;
	}
		
	}
	
	/*  TransactionResponse For the CSV file*/
	private TrasactionResponse transactionDTO(TrasactionDto resultprocess) {
	   	   TrasactionResponse response = new TrasactionResponse();  
	   	response.setProcessingFee(resultprocess.getProcessingFee());
	   	response.setClientId(resultprocess.getClientId());
	   	response.setPriority(resultprocess.getPrivateFlag());
	   	response.setTransactionDate(resultprocess.getTransactionDate());
        response.setTransactionType(resultprocess.getTransactionType());
	    return  response;	     
}
	
	/* checkIntraDayTransaction is used for checking clientid,securityid and transactiondate are same */
	public List<TrasactionDto> checkIntraDayTransaction (List<TrasactionDto> trasactionData){
	
		 return trasactionData.stream().flatMap(i -> {
	        final AtomicInteger concurrencyatomic = new AtomicInteger();
	        final List<TrasactionDto> duplicatedtransaction = new ArrayList<>();

	        trasactionData.forEach(t -> {

	            if (t.getClientId().equals(i.getClientId()) && t.getTransactionDate().equals(i.getTransactionDate()) &&
	            	 t.getSecurityId().equals(i.getSecurityId())) {
	            	concurrencyatomic.getAndIncrement();
	            }

	            if (concurrencyatomic.get() == 2) {
	            	duplicatedtransaction.add(i);
	            }

	        });

	        return duplicatedtransaction.stream().distinct();
	    }).collect(Collectors.toList());
		}
	
	

		
}
