package com.revature.bankingproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Menu for banking program
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Login");
		System.out.println("4: Admin Login");
		
		
		//read in option selected for menu
		try(Scanner sc = new Scanner(System.in);) {
			int response = sc.nextInt();
			String username, password;
			int customerLoggedInId = 0;
			switch (response) {
			case 1://take in customer account info and try to create account
				System.out.println("Customer Sign Up Page");
				System.out.println("-----------------------");
				System.out.println("Enter username:");
				username = sc.next();
				System.out.println("Enter password:");
				password = sc.next();
				
				Customer createCustomer = new Customer();
				String createAccountResult = createCustomer.signUpForServices(username, password);
				System.out.println(createAccountResult);
				if(!(createAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				break;
			case 2:
				System.out.println("Enter username: ");
				username = sc.next();
				System.out.println("Enter password: ");
				password = sc.next();
				Customer loginCustomer = new Customer();
				String loginResult = loginCustomer.login(username, password);
				
				//check if login result is password incorrect
				if(!(loginResult.equals("Password incorrect."))){
					System.out.println(loginResult);
				}
				//check if login result is username not found
				else if(!(loginResult.equals("Username not found."))){
					System.out.println(loginResult);
				}
				//check if login result are exceptions
				else if(!(loginResult.equals("General Exception"))){
					break;
				}
				else if(!(loginResult.equals("IO Exception"))){
					break;
				}
				else{
					customerLoggedInId = Integer.parseInt(loginResult);//save customerId
					loggedInMenu(customerLoggedInId);//call loggedin menu with customerId
				}
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
	
	public static void loggedInMenu(int customerId){
		System.out.println(customerId);
	}
}
