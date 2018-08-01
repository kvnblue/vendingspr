package com.vendingmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Title:VendingMachineApplication
 * 
 * Description: Application runner class which boots spring default and custom
 * loader
 * 
 * @author Kalai
 *
 */
@SpringBootApplication(scanBasePackages = "com.vendingmachine")
@EnableWebMvc
public class VendingMachineApplication {

	/**
	 * Method to start the vending machine application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);

	}

}
