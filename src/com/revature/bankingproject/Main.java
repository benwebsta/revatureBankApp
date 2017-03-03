package com.revature.bankingproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//presents options for banking app
		menuOption();
		
		//read in option selected for menu
		try(Scanner sc = new Scanner(System.in);) {
			int response = sc.nextInt();
			
			switch (response) {
			case 1:
				//presents options for creating account
				String createAccountResult = createAccountOption();
				
				System.out.println(createAccountResult);
				if(!(createAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				else if((createAccountResult.equals("Account created successfully!"))){
					int loginResult = 0;
					
					while(loginResult == 0)
						loginResult = loginOption();
					System.out.println(loginResult);
				}
				break;
			case 2:
				int loginResult = 0;
				
				while(loginResult == 0)
					loginResult = loginOption();
				System.out.println(loginResult);
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
	
	public static String createAccountOption(){
		String username, password;
		Scanner sc = new Scanner(System.in);
		System.out.println("Customer Sign Up Page");
		System.out.println("-----------------------");
		System.out.println("Enter username:");
		username = sc.next();
		System.out.println("Enter password:");	
		password = sc.next();
		
		Customer createCustomer = new Customer();
		String createAccountResult = createCustomer.signUpForServices(username, password);
		return createAccountResult;
	}
	
	public static int loginOption(){
		int customerLoggedInId = 0;
		Scanner sc = new Scanner(System.in);
		String username, password;
		System.out.println("Enter username: ");
		username = sc.next();
		System.out.println("Enter password: ");
		password = sc.next();
		
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
		return customerLoggedInId;
		
	}
	public static void loggedInMenu(int customerId){
		System.out.println(customerId);
		
	}
	
}
