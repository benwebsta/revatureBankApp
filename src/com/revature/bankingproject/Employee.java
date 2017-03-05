package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Employee {
	
	private int employeeId;
	
	/**
	 * Create customer account checking to see if username is in database
	 * @param username the username for employee
	 * @param password password for employee
	 * @return the String result of the creation attempt
	 */
	public String createEmployeeAccount(String username, String password) {
		String success;// whether the sign up was successful

		// assign instance variables to the input from main
		this.employeeId = username.hashCode();
		
		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line;
			try {

				// loop through every line in the file to check for username
				while ((line = br.readLine()) != null) {

					int location = 0;
					int colonCount = 0;// switch variable to see how many colon you've seen
					String currentUserName;
					String dataType = "";

					// loop through every character in the line, check for username match
					for (int i = 0; i < line.length(); i++) {

						// when we find a colon or end of line, there is a word
						if (line.charAt(i) == ':' || i == line.length() - 1) {
							colonCount++;// increment colon every time we find a
											// word

							// get the type of data on that line
							if (colonCount == 1) {
								dataType = line.substring(location, i);

								// if it is not a correct data type in data file
								// break
								if (!(dataType.equals("customer"))) {
									break;
								}
								;
							}

							// only check customer data types
							if ((dataType.equals("customer") || dataType.equals("employee")) && colonCount == 3) {
								currentUserName = line.substring(location, i);
								// if username is in file, throw custom
								// exception
								if (username.equals(currentUserName)) {
									throw new UserExistsException("This username is already in use.");
								}
							}
							location = i + 1;
						}
					}
				}

				// add employee account to data with id, username, and password
				bw.write("employee:" + this.employeeId + ":" + username + ":" + password + ":" + username.hashCode());
				bw.newLine();
				success = "Account created successfully!";
			} catch (UserExistsException e) {
				success = e.getMessage();
			}
		} catch (IOException e) {
			success = "Unable to create account.";
			System.out.println("IO Exception");
		} catch (Exception e) {
			success = "Unable to create account.";
			System.out.println("General Exception");
		}

		return success;
	}


}
