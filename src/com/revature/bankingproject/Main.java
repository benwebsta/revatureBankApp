package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
					loginOption(sc);
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
	
	/**
	 * Presents main menu option
	 */
	public static void menuOption(){
		//Menu for banking program
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Login");
		System.out.println("4: Admin Login");
	}
	
	/**
	 * Tries to create a customer account, returns the result
	 * 
	 * @param sc input scanner to read user input
	 * @return String the result of signing up for an account
	 */
	public static String createAccountOption(BufferedReader sc){
		String username = "";
		String password = "";
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
		
		//call sign up method from Customer class
		Customer createCustomer = new Customer();
		String createAccountResult = createCustomer.signUpForServices(username, password);
		return createAccountResult;
	}
	
	/**
	 * Tries to login with username and password input
	 * 
	 * @param sc
	 * @return
	 */
	public static int loginOption(BufferedReader sc){
		int customerLoggedInId = 0;
		
		try{
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			
			//tries to login with username and password input
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
	
	/**
	 * Presents menu for logged in customer
	 */
	public static void loggedInMenuOption(){
		//Menu for logged in customer
		System.out.println("Welcome to your account");
		System.out.println("----------------------");
		System.out.println("1: Sign up for Savings Acccount");
		System.out.println("2: Sign up for Checking Account");
		System.out.println("3: View Accounts");
	}

	/**
	 * Deals with customer logged in menu input
	 * 
	 * @param customerId the foreing key for the account
	 * @param sc input scanner
	 */
	public static void loggedInMenu(int customerId, BufferedReader sc){
		loggedInMenuOption();
		
		try{
			int response = Integer.parseInt(sc.readLine());
			
			switch(response){
			case 1://create savings
				Customer createSavingsAccount = new Customer();
				String createSavingsAccountResult;
				createSavingsAccountResult = createSavingsAccount.signUpForSavingsAccount(customerId);
				System.out.println(createSavingsAccountResult);
				break;
			case 2://create checking
				Customer createCheckingAccount = new Customer();
				String createCheckingAccountResult;
				createCheckingAccountResult = createCheckingAccount.signUpForCheckingAccount(customerId);
				System.out.println(createCheckingAccountResult);
				break;
			case 3://view accounts
				Customer viewAccounts = new Customer();
				ArrayList<String> viewAccountsResult;
				viewAccountsResult = viewAccounts.getAccountsForCustomer(customerId);
				
				String accountType;
				//2 accounts
				if(viewAccountsResult.size() == 4){
					System.out.println("Enter account to access:");
					//savings account is option 1
					if(viewAccountsResult.get(0).equals("savings")){
						System.out.println("1: " + viewAccountsResult.get(0) + ": " + viewAccountsResult.get(1));				
						System.out.println("2: " + viewAccountsResult.get(2) + ": " + viewAccountsResult.get(3));
					}
					else{
						System.out.println("1: " + viewAccountsResult.get(2) + ": " + viewAccountsResult.get(3));
						System.out.println("2: " + viewAccountsResult.get(0) + ": " + viewAccountsResult.get(1));
					}
				}
				// 1 account
				else if(viewAccountsResult.size() == 2){
					System.out.println("Enter account to access:");
					if(viewAccountsResult.get(0).equals("savings")){
						System.out.println("1: " + viewAccountsResult.get(0) + ": " + viewAccountsResult.get(1));
					}
					else{
						System.out.println("2: " + viewAccountsResult.get(0) + ": " + viewAccountsResult.get(1));
					}	
				}
				accountType = sc.readLine();
				if(accountType.equals("1"))//1 for savings
					accountType = "savings";
				else if (accountType.equals("2"))//2 for checking
					accountType = "checking";
				
				int manageBalance = 0;
				
				System.out.println("Do you want to deposit or withdraw?");
				System.out.println("1: Deposit");
				System.out.println("2: Withraw");
				
				//deposit or withdraw option
				manageBalance = Integer.parseInt(sc.readLine());
				if(manageBalance == 1)
					depositMoney(customerId, sc, accountType);
				else if(manageBalance == 2)
					withdrawMoney(customerId, sc, accountType);
				else
					System.out.println("not a correct option");
				
				
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
	
	/**
	 * Deposit money into account type specified in loggedinmenu
	 * @param customerId foreign key for customer account
	 * @param sc input scanner 
	 * @param accountType savings or checking picked by customer
	 */
	public static void depositMoney(int customerId, BufferedReader sc, String accountType){
		Customer depositMoney = new Customer();
		int amount = 0;
		
		try{
			System.out.println("How much money do you want to deposit?");
			amount = Integer.parseInt(sc.readLine());
		}
		catch(IOException e){
			System.out.println("IO Exception");
		}
		
		int newBalance = depositMoney.depositMoney(customerId, accountType, amount);
		System.out.println("New Balance: $" + newBalance);
	}
	
	/**
	 * Withdraw money from account type specified in logginmenu
	 * @param customerId foreign key for customer account
	 * @param sc input scanner
	 * @param accountType savings or checking picked by customer
	 */
	public static void withdrawMoney(int customerId, BufferedReader sc, String accountType){
		Customer withdrawMoney = new Customer();
		int amount = 0;
		
		try{
			System.out.println("How much money do you want to withdraw?");
			amount = Integer.parseInt(sc.readLine());
		}
		catch(IOException e){
			System.out.println("IO Exception");
		}
		
		int newBalance = withdrawMoney.withdrawMoney(customerId, accountType, amount);
		System.out.println("New Balance: $" + newBalance);
	}
}
