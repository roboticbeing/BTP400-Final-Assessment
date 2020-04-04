/**
 * @author Jessica Krishtul
 */

package com.seneca.accounts;

import java.math.BigDecimal;

public class Chequing extends Account {
	
//In your implementation, a
//Chequing object uses an array (not an ArrayList) to keep track of all the
//transactions (i.e. deposits and withdrawals) made on the account.
protected BigDecimal[] transactions;
protected int maxTransactions;
protected BigDecimal serviceCharge;
	
/**
 * Zero argument constructor
 * uses default constructor of Account
 * Initializes service charge to $ 0.25
 * and maximum number of transactions to 3
 */
public Chequing() {
	super();
	serviceCharge = BigDecimal.valueOf(0.25);
	maxTransactions = 3;
	}

/**
 * 5-Argument Constructor
 * @param fullName
 * @param accountNumber
 * @param balance
 * @param serviceCharge
 * @param maxTransactions
 */
public Chequing(String fullName, String accountNumber, double balance, double serviceCharge, int maxTransactions) {
	super(fullName, accountNumber, balance);
	this.serviceCharge = BigDecimal.valueOf(serviceCharge);
	this.maxTransactions = maxTransactions;
	
}

/**
 * override equals
 * @param: obj
 * @return: false
 */
public boolean equals(Object obj) {
    if (obj instanceof Chequing) {
        return super.equals(obj) && maxTransactions == ((Chequing) obj).maxTransactions &&
        		this.serviceCharge.equals(serviceCharge);
    }
    return false;	
}

/**
 * override toString
 * @return: s
 */
public String toString() {

    StringBuffer s = new StringBuffer();
    String transactionsString[] = new String[transactions.length];
    s.append(super.toString());
	s.append(
		 "Account Type           : CHQ \n" +
		 "Service Charge         :  $" + String.format("$%.2f", serviceCharge) + "\n" +
		 "Total Charges          : $" + String.format("$%.2f", this.getTotalCharges()) + "\n" + 
		 "List of Transactions   : ");
	        for(int i = 0; i < transactions.length; ++i){
	            transactionsString[i] = String.format("%+.2f", transactions[i]);
	        }
	        s.append(String.join(", ", transactionsString) + "\n" + 
		 "Final Balance          : $" + String.format("%.2f", this.getBalance()) + "\n"
			);
	   
       return s.toString();
    }

/**
 * override hashCode
 * @return: hash
 */
public int hashCode() {     
    int hash = super.hashCode();
    Integer mt = new Integer(maxTransactions);
    hash = 89 * hash + (mt != null ? mt.hashCode() : 0);     
    hash = 89 * hash + (this.serviceCharge != null ? this.serviceCharge.hashCode() : 0);     
    return hash;     
}   

/**
 * override withdraw from Account
 * @param: amount
 * @return: super.withdraw(amount)/false
 */
public boolean withdraw(double amount) {

	//(The amount of a withdrawal should be stored as a negative number.) 
	//the maximum number of transactions will be exceeded, 
	//the balance would become negative if the transaction were completed.
	if(transactions.length > maxTransactions) {
		//The withdraw( ) method will return false if the transaction cannot be
		//completed.
		return false;
	}
	else {
		boolean success = super.withdraw(amount);
		if(success) {
			BigDecimal[] arr = new BigDecimal[transactions.length + 1];
			for (int i = 0; i < transactions.length; i++) {
				arr[i] = transactions[i];
			}
			amount += amount * -1;
			arr[transactions.length] = BigDecimal.valueOf(amount);
			transactions = new BigDecimal[arr.length];
			for(int i = 0; i < arr.length; i++) {
				transactions[i] = arr[i];
			}
		}
		return success;
	}
}

/**
 * override deposit from Account class
 * @param: amount
 */
public void deposit(double amount){
    if (transactions.length < maxTransactions && amount >= 0){
        super.deposit(amount);
        BigDecimal[] arr = new BigDecimal[transactions.length + 1];
        for (int i = 0; i < transactions.length; ++i){
            arr[i] = transactions[i];
        }
        arr[transactions.length] = new BigDecimal(amount);
        transactions = new BigDecimal[arr.length];
        for (int i = 0; i < arr.length; ++i){
            transactions[i] = arr[i];
        }
    }
}

/**
 * Get Total Charges
 * @return: 0 / serviceCharge.multiply(new BigDecimal(transactions.length)).doubleValue()
 */
public double getTotalCharges(){
    if (transactions.length != 0)
        return (serviceCharge.multiply(new BigDecimal(transactions.length)).doubleValue());
    else
        return 0;
}

/**
 * override getBalance from account so that it returns final balance of account
 * @return: 0 / super.getBalance() - this.getTotalCharges()
 */
public double getBalance() {
	//The final balance is calculated by subtracting the
	//total amount of service charges from the current balance. (The final
	//balance will become negative if the current balance is zero and the service
	//charges are greater than zero.)
	if (transactions.length != 0)
        return super.getBalance() - this.getTotalCharges();
    else
        return 0;
}

}


