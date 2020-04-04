/**
 * @author Jessica Krishtul
 * @author Jeasung
 */
package com.seneca.business;

import java.util.ArrayList; 
import com.seneca.accounts.Account;

public class Bank {

	private String name;
	private ArrayList<Account> list = new ArrayList<Account>();

	/**
	 * Zero argument constructor
	 */
	public Bank() {
		this.setName("Seneca@York");
		this.setList(new ArrayList<Account>());
	}

	/**
	 * One argument constructor
	 * @param newName
	 */
	public Bank(String newName) {
		this.setName(newName);
		this.setList(new ArrayList<Account>());
	}

	/**
	 * add account to bank
	 * @param newAccount
	 * @return true/false
	 */
	public boolean addAccount(Account newAccount) {
		//Account can't be null
		if (newAccount == null)
			return false;

		//Account can't have identical account numbers
		for(int i = 0; i < list.size(); i++) {
			if (newAccount.getAccountNumber().equals(list.get(i).getAccountNumber()))
				return false;
		}

		list.add(newAccount);
		return true;
	}

	/**
	 * Search account by balance
	 * @param bal
	 * @return accounts / new account[0]
	 */
	public Account[] searchByBalance(double bal) {
		Account[] accounts;
		int size = 0;
		//check how many accounts have the same balance
		for (int i = 0; i < list.size(); i++) {
			double accBalance = (double)list.get(i).getBalance();
			if (bal == accBalance) {
				size++;
			}
		}
		if (size == 0) {
			return new Account[0];
		} else {
			accounts = new Account[size];
			int index = 0;
			//store size of array
			for (int i = 0; i < list.size(); i++) {
				int accBalance = (int)list.get(i).getBalance();
				if (bal == accBalance) {
					accounts[index++] = list.get(i);
				}
			}
		}
		return accounts;
	}

	/**
	 * search accounts by account name
	 * @param name
	 * @return account / new Account[0]
	 */
	public Account[] searchByAccountName(String name) {
		Account[] accounts;
		int size = 0;
		//check how many accounts have the same balance
		for (int i = 0; i < list.size(); i++) {
			String accName = (String)list.get(i).getFullName();
			if (name.equals(accName)) {
				size++;
			}
		}
		if (size == 0) {
			return new Account[0];
		} else {
			accounts = new Account[size];
			int index = 0;
			//store size of array
			for (int i = 0; i < list.size(); i++) {
				String accName = (String)list.get(i).getFullName();
				if (name == accName) {
					accounts[index++] = list.get(i);
				}
			}
		}
		return accounts;
	}

	/**
	 * get all accounts
	 * @return accounts
	 */
	public Account[] getAllAccounts(){
		Account[] accounts = new Account[list.size()];
		for (int i = 0; i < list.size(); ++i){
			accounts[i] = list.get(i);
		}
		return accounts;
	}

	/**
	 * remove an account
	 * @param accountNumber
	 * @return removedAcc
	 */
	public Account removeAccount(String accountNumber) {
		Account removedAcc = null;

		if (accountNumber != null) {
			for (int i = 0; i < this.list.size(); i++) 
			{
				if (this.list.get(i).getAccountNumber() == accountNumber) {
					removedAcc = list.get(i);
					list.remove(i);
					break;
				}
			}
		}

		return removedAcc;
	}


	/**
	 * override toString method
	 * @return: s
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < list.size(); i++) {
			s += (i + 1) + ". number: " + list.get(i).getAccountNumber() + ", " + "name: " +
					list.get(i).getFullName() + ", balance: $" + (int)list.get(i).getBalance() + "\n";
		}
		return s;
	}

	/**
	 * override equals
	 * @param z
	 * @return result
	 */
	public boolean equals( Object z ) {

		boolean result = false;

		if ( z instanceof Bank ) {

			Bank z2 = (Bank) z;

			if ((z2.name.equals(name)) &&
					(z2.list.equals(list)))

				result = true;
		}
		return result;
	}

	/**
	 * override hashcode
	 * @return hash
	 */
	public int hashCode() {
		int hash = 17;

		hash = hash * 37 + name.hashCode();    
		hash = hash * 37 + list.hashCode();

		return hash;

	}

	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get accounts array
	 * @return list
	 */
	public ArrayList<Account> getList() {
		return list;
	}

	/**
	 * set accounts array
	 * @param list
	 */
	public void setList(ArrayList<Account> list) {
		this.list = list;
	}


}
