package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		Employee emp = new Employee();
		
		//presents options for banking app
		menuOption();
		
		//read in option selected for menu
		try(BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));) {
			int response = Integer.parseInt(sc.readLine());
			
			switch (response) {
			case 1:
				//presents options for creating account
				String createCustomerAccountResult = createCustomerAccountOption(sc);
				
				System.out.println(createCustomerAccountResult);
				if(!(createCustomerAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				else if((createCustomerAccountResult.equals("Account created successfully!"))){
					int loginResult = 0;
					
					while(loginResult == 0){
						loginResult = customerLoginOption(sc);
					}
					customerLoggedInMenu(loginResult, sc);;
				}
				break;
			case 2:
				int customerLoginResult = 0;
				
				while(customerLoginResult == 0)
					customerLoginResult = customerLoginOption(sc);
				customerLoggedInMenu(customerLoginResult, sc);
				break;
			case 3:
				//presents options for creating account
				String createEmployeeAccountResult = createEmployeeAccountOption(sc);
				
				System.out.println(createEmployeeAccountResult);
				if(!(createEmployeeAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				else if((createEmployeeAccountResult.equals("Account created successfully!"))){
					int employeeLoginResult = 0;
					
					while(employeeLoginResult == 0){
						employeeLoginResult = employeeLoginOption(sc);
					}
					employeeLoggedInMenu(employeeLoginResult, sc);
				}
				break;
			case 4:
				int employeeLoginResult = 0;
				
				while(employeeLoginResult == 0)
					employeeLoginResult = employeeLoginOption(sc);
				employeeLoggedInMenu(employeeLoginResult, sc);
				break;
			case 5:
				System.out.println("Admin sign up");
				break;
			case 6:
				System.out.println("admin login");
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
		System.out.println("3: Employee Sign up");
		System.out.println("4: Employee Login");
		System.out.println("5: Admin Sign up");
		System.out.println("6: Admin Login");
	}
	
	/**
	 * Tries to create a customer account, returns the result
	 * 
	 * @param sc input scanner to read user input
	 * @return String the result of signing up for an account
	 */
	public static String createCustomerAccountOption(BufferedReader sc){
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
	 * @param sc input scanner
	 * @return integer value of customer id
	 */
	public static int customerLoginOption(BufferedReader sc){
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
	public static void customerLoggedInMenuOption(){
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
	public static void customerLoggedInMenu(int customerId, BufferedReader sc){
		customerLoggedInMenuOption();
		
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

	/**
	 * attempts to create employee account
	 * @param sc input scanner
	 * @return String result of what happened
	 */
	public static String createEmployeeAccountOption(BufferedReader sc){
		String username = "";
		String password = "";
		try
		{
			System.out.println("Employee Sign Up Page");
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
		Employee createEmployee = new Employee();
		String createEmployeeResult = createEmployee.createEmployeeAccount(username, password);
		return createEmployeeResult;
	}

	/**
	 * Tries to login with username and password input
	 * 
	 * @param sc input scanner
	 * @return integer of employee id
	 */
	public static int employeeLoginOption(BufferedReader sc){
		int employeeLoggedInId = 0;
		
		try{
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			
			//tries to login with username and password input
			Employee loginEmployee = new Employee();
			String employeeLoginResult = loginEmployee.login(username, password);
			
			//check if login result is password incorrect
			if((employeeLoginResult.equals("Password incorrect."))){
				System.out.println(employeeLoginResult);
				employeeLoggedInId = 0;
			}
			//check if login result is username not found
			else if((employeeLoginResult.equals("Username not found."))){
				System.out.println(employeeLoginResult);
				employeeLoggedInId = 0;
			}
			//check if login result are exceptions
			else if((employeeLoginResult.equals("General Exception"))){
				System.out.println("General Exception");
				employeeLoggedInId = 0;
			}
			else if((employeeLoginResult.equals("IO Exception"))){
				System.out.println("IO Exception");
				employeeLoggedInId = 0;
			}
			else{
				employeeLoggedInId = Integer.parseInt(employeeLoginResult);//save customerId
			}
		}
		catch(IOException e){
			System.out.println("test");
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return employeeLoggedInId;
		
	}
	
	/**
	 * Presents menu for logged in employee
	 */
	public static void employeeLoggedInMenuOption(){
		//Menu for logged in employee
		System.out.println("Employee Menu: Enter Selection");
		System.out.println("------------------------------");
		System.out.println("1: View all of your customers.");
		System.out.println("2: See your customer's account applications to approve/decline.");
	}

	/**
	 * Handle logged in employee selections
	 * @param employeeId the unique employee id
	 * @param sc the input scanner
	 */
	public static void employeeLoggedInMenu(int employeeId, BufferedReader sc){
		employeeLoggedInMenuOption();
		
		try{
			int response = Integer.parseInt(sc.readLine());
			
			switch(response){
			case 1://view customer accounts
				Employee employee = new Employee();
				//get array list of customer's accounts tied to employee
				ArrayList<String[]> customerAccounts = employee.viewCustomerAccounts(employeeId);
				
				//display list in visual way
				for(int i = 0; i < customerAccounts.size(); i++){
					String[] customerStringArray = customerAccounts.get(i);
					if((!(customerStringArray[0].equals("savings"))) && (!(customerStringArray[0].equals("checking"))))
							System.out.println("-------------------------------");
					System.out.println(customerStringArray[0] + " " + customerStringArray[1]);
				}
				System.out.println("-------------------------------");
				break;
			case 2://see customer's account applications to approve/decline
				approveAccountApplications(sc);
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void approveAccountApplications(BufferedReader sc){
		Employee employee = new Employee();
		String[] application = employee.getAccountApplications(sc);
		
		if(application[0].equals("")){
			System.out.println("No accounts to approve.");
		}
		else{
			if(application[2].equals("true")){
				System.out.println(application[0] + " account " + application[1] + " approved!");
			}
			else if(application[2].equals("false")){
				System.out.println(application[0] + " account " + application[1] + " declined!");
			}
		}
	}
	
}
