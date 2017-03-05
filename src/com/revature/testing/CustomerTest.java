package com.revature.testing;

import static com.revature.bankingproject.Main.mainMenuOption;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingproject.Customer;

public class CustomerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Test
	public void test() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\testing\\CustomerTestCLI.txt"));)
			{
			//test create a customer account
			Customer customer = new Customer();
			String username = br.readLine();
			String password = br.readLine();
			String createCustomer = customer.signUpForServices(username, password);
			assertEquals("Account created successfully!", createCustomer);

			//test login with a customer username and password
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
