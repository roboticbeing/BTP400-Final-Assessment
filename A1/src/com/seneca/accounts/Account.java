/**
 * @author: Jessica Krishtul
 * @author: Jeasung
 */

package com.seneca.accounts;

import java.math.*;

public class Account {
	private String fullName;
	private String accountNumber;
	private BigDecimal balance; 

	/**
	 * Zero argument constructor
	 */
	public Account() {
		this("", "", 0.00);
	}

	/** 
	 * Three argument constructor
	 * @param: fullName, accountNumber, and balance
	 */
	public Account(String fullName, String accountNumber, double balance) {
		setFullName(fullName);
		setAccountNumber(accountNumber);
		setAccountBalance(balance);
	}

	//Getters

	/**
	 * Get full name
	 * @return: fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Get First Name
	 * @return: fullName.substring(0, fullName.indexOf(" "));
	 */
	public String getFirstName() {
		if(fullName.equals(""))
			return "";
		else return fullName.substring(0, fullName.indexOf(" "));
	}

	/**
	 * Get Last Name
	 * @return: fullName.substring(fullName.lastIndexOf(' ')+ 1);
	 */
	public String getLastName() {
		if(fullName.equals(""))
			return "";
		else return fullName.substring(fullName.lastIndexOf(' ')+ 1);
	}

	/**
	 * Get account number
	 * @return: accountNumer
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Get account balance
	 * @return: balance
	 */
	public double getBalance() {
		return balance.doubleValue();
	}

	//Setters

	/**
	 * Set Full Name
	 * @param name
	 */
	//If a null value is passed as a parameter, set it as an empty string.
	public void setFullName(String name) {
		if(name != null)
			this.fullName = name;
		else
			this.fullName = "";
	}

	/**
	 * Set Account Number
	 * @param id
	 */

	//If a null value is passed as a parameter, set it as an empty string.
	public void setAccountNumber(String id) {
		if(id != null)
			this.accountNumber = id;
		else
			this.accountNumber = "";
	}

	/**
	 * Set account balance
	 * @param bal
	 */
	//If a negative value is passed as a parameter, set it as 0.
	public void setAccountBalance(double balance) {
		if(!(balance < 0))
			this.balance = BigDecimal.valueOf(balance);
		else
			this.balance = BigDecimal.ZERO;
	}

	/**
	 * System.out.println format
	 * @return: s
	 */

	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(
				"Name           : " + getLastName() + ", " + getFirstName() + "\n" +
						"Number         : " + accountNumber + "\n" +
						"Current Balance: $" + balance + "\n" 
				);

		return s.toString();
	}

	/**
	 * Override hashcode
	 * @return: hash
	 */
	public int hashCode() {
		int hash = 17;

		hash = hash * 37 + balance.hashCode();    
		hash = hash * 37 + fullName.hashCode();
		hash = hash * 37 + accountNumber.hashCode();
		return hash;

	}

	/**
	 * Override equals
	 * Account objects are equal if their object references are equal
	 * @param: z
	 * @return: result
	 */
	public boolean equals( Object obj ) {
		if (obj != null && obj.getClass() == getClass()) {
			return balance == ((Account) obj).balance &&
					this.fullName.equals(fullName) &&
					this.accountNumber.equals(accountNumber);     		
		}
		return false;
	}

	/**
	 * Withdraw amount from balance
	 * @param amount
	 * @return true or false
	 */
	public boolean withdraw(double amount) {
		//	 If the deposit( ) or withdraw( ) method receives a negative amount, it
		//	 will not update the (current) balance. 
		if (amount < 0) {
			return false;
		} 
		//		return false and the balance will not be updated if an account does not have
		//			 enough money to complete the withdrawal.
		else {
			BigDecimal bdAmount = new BigDecimal(amount);
			if (balance.subtract(bdAmount).compareTo(BigDecimal.ZERO) == -1) {
				return false;
			} else {
				balance = this.balance.subtract(BigDecimal.valueOf(amount));
			}
			return true;
		}
	}

	/**
	 * deposit amount into balance
	 * @param amount
	 */
	public void deposit( double amount ) {
		//	 If the deposit( ) or withdraw( ) method receives a negative amount, it
		//	 will not update the (current) balance. 
		if(!(amount <= 0))
			balance = this.balance.add(BigDecimal.valueOf(amount));
	}


}

