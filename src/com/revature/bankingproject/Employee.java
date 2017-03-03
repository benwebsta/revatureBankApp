package com.revature.bankingproject;

public class Employee {
	
	static int employeeCount = 0;
	int employeeNumber = 0;
	
	public Employee(){
		employeeCount++;
		employeeNumber = employeeCount;
	}

}
