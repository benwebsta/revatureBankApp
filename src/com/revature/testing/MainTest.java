package com.revature.testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingproject.Customer;
import com.revature.bankingproject.Main;

import static com.revature.bankingproject.Main.*;

public class MainTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Test
	public void test() {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\testing\\mainTestCLI.txt"));)
			{
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			mainMenuOption(br);
			
			Customer viewAccounts = new Customer();
			ArrayList<String> viewAccountsResult = viewAccounts.getAccountsForCustomer("boblikesunicorns".hashCode());	
			assertEquals("savings", viewAccountsResult.get(0));
			assertEquals("$55", viewAccountsResult.get(1));
			assertEquals("checking", viewAccountsResult.get(2));
			assertEquals("$1300", viewAccountsResult.get(3));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
