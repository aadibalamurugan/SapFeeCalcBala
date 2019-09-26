package com.sapient.calc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.sapient.calc.Bean.TrasactionDto;
import com.sapient.calc.service.FeeCalcService;
import com.sapient.calc.service.FeeCalcServiceImpl;



@Controller
public class FeeCalcController 
{
	  @Autowired(required=true)
	  FeeCalcService service;
	  
	  
    final static String inputfile="inputData/sampleinput.csv";
   
  @Scheduled(cron = "*/5 * * *  * *")  
  public void data() {
	  System.out.println("Scheduler is started...");
	  System.out.println("controller start's ...");
	  String readSingleData="";
     try {
	  ClassLoader csl= new FeeCalcController().getClass().getClassLoader();
	  File file = new File(csl.getResource(inputfile).getFile());
	  if(file.isFile()) {
		  BufferedReader readData = new BufferedReader(new FileReader(file));
		  readData.readLine();
			List<TrasactionDto> transDataList = new ArrayList<TrasactionDto>();
		while((readSingleData =readData.readLine()) != null) {
			String[] str =readSingleData.split(",");
			
			TrasactionDto trasactionData = new TrasactionDto();
			trasactionData.setExternalTrasaction(str[0]);
			trasactionData.setClientId(str[1]);
			trasactionData.setSecurityId(str[2]);
			trasactionData.setTransactionType(str[3]);
			trasactionData.setTransactionDate(str[4]);
			trasactionData.setMarketValue(str[5]);
			trasactionData.setPrivateFlag(str[6]);
			transDataList.add(trasactionData);			
		  }
		service.gettransaction(transDataList);

	  }
	  else {
		  System.out.println("File is not present to read for your application...");
	  }
	     } catch (IOException e) {
	// TODO Auto-generated catch block
	System.out.println("FeeCalcController :" + e);
	//e.printStackTrace();
     }
    }
	
}
