package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {

		//presents options for banking app
		menuOption();
		
		//read in option selected for menu
		try(BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));) {
			int response = Integer.parseInt(sc.readLine());
			
			switch (response) {
			case 1:
				//presents options for creating account
				String createAccountResult = createAccountOption(sc);
				
				System.out.println(createAccountResult);
				if(!(createAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				else if((createAccountResult.equals("Account created successfully!"))){
					int loginResult = 0;
					
					while(loginResult == 0){
						loginResult = loginOption(sc);
					}
					System.out.println(loginResult);
				}
				break;
			case 2:
				int loginResult = 0;
				
				while(loginResult == 0)
					loginResult = loginOption(sc);
				loggedInMenu(loginResult, sc);
				break;
			case 3:
				System.out.println("Employee Login");
				break;
			case 4:
				System.out.println("Admin Login");
				break;
			default:
				System.out.println("You didn't enter a correct number option");
				main(null);//give menu options again
				break;
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("General Exception");
		}
	}
	
	public static void menuOption(){
		//Menu for banking program
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Login");
		System.out.println("4: Admin Login");
		
	}
	
	public static void loggedInMenuOption(){
		//Menu for logged in customer
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Sign up for Savings Acccount");
		System.out.println("2: Sign up for Checking Account");
		System.out.println("3: View Accounts");
	}
	
	public static String createAccountOption(BufferedReader sc){
		String username = "test";
		String password = "test";
		
		try
		{
			System.out.println("Customer Sign Up Page");
			System.out.println("-----------------------");
			System.out.println("Enter username:");
			username = sc.readLine();
			System.out.println("Enter password:");	
			password = sc.readLine();
		}
		catch(IOException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		Customer createCustomer = new Customer();
		String createAccountResult = createCustomer.signUpForServices(username, password);
		return createAccountResult;
	}
	
	public static int loginOption(BufferedReader sc){
		int customerLoggedInId = 0;
		
		try{
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			
			Customer loginCustomer = new Customer();
			String loginResult = loginCustomer.login(username, password);
			
			//check if login result is password incorrect
			if((loginResult.equals("Password incorrect."))){
				System.out.println(loginResult);
				customerLoggedInId = 0;
			}
			//check if login result is username not found
			else if((loginResult.equals("Username not found."))){
				System.out.println(loginResult);
				customerLoggedInId = 0;
			}
			//check if login result are exceptions
			else if((loginResult.equals("General Exception"))){
				System.out.println("General Exception");
				customerLoggedInId = 0;
			}
			else if((loginResult.equals("IO Exception"))){
				System.out.println("IO Exception");
				customerLoggedInId = 0;
			}
			else{
				customerLoggedInId = Integer.parseInt(loginResult);//save customerId
			}
		}
		catch(IOException e){
			System.out.println("test");
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return customerLoggedInId;
		
	}
	public static void loggedInMenu(int customerId, BufferedReader sc){
		loggedInMenuOption();
		
		try{
			int response = Integer.parseInt(sc.readLine());
			
			switch(response){
			case 1:
				Customer createSavingsAccount = new Customer();
				String createSavingsAccountResult;
				createSavingsAccountResult = createSavingsAccount.signUpForSavingsAccount(customerId);
				System.out.println(createSavingsAccountResult);
				break;
			case 2:
				Customer createCheckingAccount = new Customer();
				String createCheckingAccountResult;
				createCheckingAccountResult = createCheckingAccount.signUpForCheckingAccount(customerId);
				System.out.println(createCheckingAccountResult);
				break;
			case 3:
				Customer viewAccounts = new Customer();
				String viewAccountsResult;
				viewAccountsResult = viewAccounts.getAccountsForCustomer(customerId);
				System.out.println(viewAccountsResult);
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}
		catch(IOException e){
			System.out.println("test");
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
}
