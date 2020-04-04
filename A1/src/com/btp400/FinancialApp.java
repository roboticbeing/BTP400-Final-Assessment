/**
 * @author Jeasung
 * @author Jessica
 */

package com.btp400;

import java.util.Scanner;
import com.seneca.accounts.*;
import com.seneca.business.*;

public class FinancialApp {
	static Bank bank = new Bank();
	public static void main(String[] args) {
		loadBank();
		displayMenu(bank.getName());
		int c = menuChoice();
		while (c != 7) {		
			if (c == 1) {
				openAnAccount();
			} else if (c == 2) {
				closeAnAccount();
			} else if (c == 3) {
				depositMoney();
			} else if (c == 4) {
				withdrawMoney();
			} else if (c == 5) {
				displayAccounts();
			} else if (c == 6) {
				displayATaxStatement();
			}
			displayMenu(bank.getName());
			c = menuChoice();
		}
		exitApp();
	}
	
	public static int menuChoice() {
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}
	
	public static void displayAccount(Account account) {
		System.out.println(account);
	}
	
	public static void displayMenu(String bankName) {
		System.out.println("Welcome to " + bank.getName() + " Bank!");
		System.out.println("1. Open an account.");
		System.out.println("2. Close an account.");
		System.out.println("3. Deposit money.");
		System.out.println("4. Withdraw money.");
		System.out.println("5. Display accounts.");
		System.out.println("6. Display a tax statement.");
		System.out.println("7. Exit");
		System.out.print("Please enter choice>");
	}
	
	public static void openAnAccount() {
		System.out.println("Please enter the account type (CHQ/GIC)>");
		Scanner input = new Scanner(System.in);
		input.useDelimiter(";");
		String selection = input.nextLine();
		if (selection.equals("GIC")) {
			System.out.println("Please enter account information at one line");
			System.out.println("(e.g. John M. Doe;A1234;1000.00;1.5;2;)");
			String fullName = input.next();
			String accountNumber = input.next();
			double balance = input.nextDouble();
			int investmentPeriod = input.nextInt();
			double annualInterestRate = input.nextDouble();
			GIC gic = new GIC(fullName, accountNumber, balance, investmentPeriod, annualInterestRate);
			bank.addAccount(gic);
			System.out.println("GIC Account opened.");
		} else if (selection.equals("CHQ")) {
			System.out.println("Please enter account information at one line");
			System.out.println("(e.g. John M. Doe;A1234;1000.00;1.5;2;)");
			String fullName = input.next();
			System.out.println(fullName);
			String accountNumber = input.next();
			System.out.println(accountNumber);
			double balance = input.nextDouble();
			System.out.println(balance);
			double serviceCharge = input.nextDouble();
			System.out.println(serviceCharge);
			int maxTransactions = input.nextInt();
			System.out.println(maxTransactions);
			Chequing chq = new Chequing(fullName, accountNumber, balance, serviceCharge, maxTransactions);
			bank.addAccount(chq);
			System.out.println("Chequing account opened.");
		}
		
	}
	
	public static void closeAnAccount() {
		System.out.print("Enter the account number>");
		Scanner input = new Scanner(System.in);
		String accNum = input.nextLine();
		Account acc = bank.removeAccount(accNum);
		System.out.println("Account number " + acc.getAccountNumber() + " has been removed." + "\n");
	}
	
	public static void depositMoney() {
		System.out.print("Enter the account number to deposit money into>");
		Scanner input = new Scanner(System.in);
		String accNum = input.nextLine();
		for (Account acc : bank.getAllAccounts()) {
			if (acc.getAccountNumber().equals(accNum)) {
				System.out.print("Enter the amount to deposit>");
				double amt = input.nextDouble();
				acc.deposit(amt);
				System.out.println("Amount " + amt + " deposited into account number " + acc.getAccountNumber());
			}
		}
	}
	
	public static void withdrawMoney() {
		System.out.print("Enter the account number to withdraw money from>");
		Scanner input = new Scanner(System.in);
		String accNum = input.nextLine();
		for (Account acc : bank.getAllAccounts()) {
			if (acc.getAccountNumber().equals(accNum)) {
				System.out.print("Enter the amount to withdraw>");
				double amt = input.nextDouble();
				acc.withdraw(amt);
				System.out.println("Amount " + amt + " withdrawn from account number " + acc.getAccountNumber());
			}
		}
	}
	
	public static void displayAccounts() {
		System.out.println("Select from one of these options (a/b/c):");
		System.out.println("(a) display all accounts with the same account name");
		System.out.println("(b) display all accounts with the same final balance");
		System.out.println("(c) display all accounts opened at the bank.");
		Scanner input = new Scanner(System.in);
		Account[] accs = null;
		String selection = input.nextLine();
		if (selection.equals("a")) {
			System.out.println("Enter an account name>");
			String accName = input.nextLine();
			accs = bank.searchByAccountName(accName);
		} else if (selection.equals("b")) {
			System.out.println("Enter a final balance>");
			double finalBalance = input.nextDouble();
			accs = bank.searchByBalance(finalBalance);
		} else if (selection.equals("c")) {
			accs = bank.getAllAccounts();
		}
		for (int i = 0; i < accs.length; i++) {
			displayAccount(accs[i]);
		}
	}
	
	public static void displayATaxStatement() {
		System.out.println("Enter an account name>");
		Scanner input = new Scanner(System.in);
		String accName = input.nextLine();
		for (Account acc : bank.searchByAccountName(accName)) {
			if (acc instanceof Taxable) {
				System.out.println(((GIC) acc).createTaxStatement());
			}
		}
	}
	
	public static void exitApp() {
		System.out.println("Goodbye.");
	}
	
	public static void loadBank() {
		bank.addAccount(new GIC("John Doe", "12345", 6000.00, 2, 0.0150));
		bank.addAccount(new GIC("Mary Ryan", "67890", 15000.00, 4, 0.0250));
		bank.addAccount(new Chequing("John Doe", "13579", 2345.00, 6.00, 100));
		bank.addAccount(new Chequing("Mary Ryan", "24680", 9876.00, 6.00, 100));
	}
	
}
