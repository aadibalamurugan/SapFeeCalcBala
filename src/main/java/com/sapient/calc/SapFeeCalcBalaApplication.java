package com.sapient.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class SapFeeCalcBalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapFeeCalcBalaApplication.class, args);
	}
	
	

	
}
