/**
 * @author Jessica Krishtul
 */

package com.seneca.accounts;

import java.math.BigDecimal;

public class GIC extends Account implements Taxable {

	protected int investmentPeriod;
	protected BigDecimal annualInterestRate;
	protected BigDecimal tax = new BigDecimal(0);

	/**
	 * Zero argument constructor uses the default constructor of the Account class
	 * It initializes the period of investment to 1 year and annual interest rate to 1.25%
	 */
	public GIC() {
		super();
		investmentPeriod = 1;
		annualInterestRate = BigDecimal.valueOf(0.0125);
	}

	/**
	 * 5-Argument Constructor
	 * @param fullName
	 * @param accountNumber
	 * @param balance
	 * @param investmentPeriod
	 * @param annualInterestRate
	 */
	public GIC(String fullName, String accountNumber, double balance, int investmentPeriod, double annualInterestRate) {
		super(fullName, accountNumber, balance);
		this.investmentPeriod = investmentPeriod;
		this.annualInterestRate = BigDecimal.valueOf(annualInterestRate);
	}

	/**
	 * override equals
	 * @param obj
	 * @return false / true
	 */
	public boolean equals(Object obj) {
		if (obj instanceof GIC) {
			return super.equals(obj) && investmentPeriod == ((GIC) obj).investmentPeriod &&
					this.annualInterestRate.equals(annualInterestRate);
		}
		return false;	
	}


	/**
	 * override toString of Account and add info related to GIC Account
	 * @return s
	 */
	public String toString() {

		StringBuffer s = new StringBuffer();
		s.append(super.toString());
		s.append(
				"Account Type           : GIC \n" +
						"Annual Interest Rate   : " + (String.format("%.2f%%", annualInterestRate.multiply(new BigDecimal(100))) + "% \n" +
								"Period of Investment   : "));
		s.append(String.format("%d " , investmentPeriod)).append((investmentPeriod <= 1) ? "year\n" : "years\n");
		s.append("Interest Income at Maturity: $" + (String.format("$%.2f\n", getInterestIncome()) + "\n" +
				"Balance at Maturity    : $" + (String.format("$%.2f\n", getBalance()))  + "\n" 	 
				));

		return s.toString();
	}

	/**
	 * override hashcode
	 * @return hash
	 */
	public int hashCode() {     
		int hash = super.hashCode();
		Integer ip = new Integer(investmentPeriod);
		hash = 89 * hash + (ip != null ? ip.hashCode() : 0);     
		hash = 89 * hash + (this.annualInterestRate!= null ? this.annualInterestRate.hashCode() : 0);     
		return hash;     
	}   

	/**
	 * Get annual interest rate
	 * @return annualInterestRate
	 */
	public double getAnnualInterestRate() {
		return this.annualInterestRate.doubleValue();
	}

	/**
	 * get investment period
	 * @return investmentPeriod
	 */
	public int getInvestmentPeriod() {
		return this.investmentPeriod;
	}

	/**
	 * get balance at maturity
	 * @return balanceAtMaturity
	 */
	public double getBalanceAtMaturity() {
		BigDecimal balanceAtMaturity = BigDecimal.valueOf(this.getBalance())
				.multiply(BigDecimal.valueOf(this.getAnnualInterestRate())
						.add(new BigDecimal(1)).pow(investmentPeriod));
		return balanceAtMaturity.doubleValue();
	}

	/**
	 * get interest income
	 * @return interestIncome
	 */
	public double getInterestIncome() {

		//Interest Income = Balance at Maturity – Starting Balance
		BigDecimal interestIncome = BigDecimal.valueOf(getBalanceAtMaturity()).subtract(BigDecimal.valueOf(this.getBalance()));
		return interestIncome.doubleValue();
	}

	/**
	 * implement calculateTax from Taxable interface
	 */
	@Override
	public void calculateTax() {
		//		Balance at Maturity = Current/Starting Balance x ( 1 + annualInterestRate ) ^ investmentPeriod
		tax = taxRate.multiply(new BigDecimal(getInterestIncome()));
	}

	/**
	 * implement getTaxAmount from Taxable interface
	 * returns the amount of tax that has been calculated
	 * Amount of Tax = Interest income x tax rate
	 * @return tax
	 */
	@Override
	public double getTaxAmount() {
		calculateTax();
		return tax.doubleValue();
	}

	/**
	 * implements createTaxStatement from Taxable interface
	 * @return s
	 */
	@Override
	public String createTaxStatement() {
		StringBuffer s = new StringBuffer();
		s.append("Tax Rate : ").append(String.format("%.0f%%", taxRate.multiply(new BigDecimal(100)))).append("\n");
		s.append("Account Number : ").append(getAccountNumber()).append("\n");
		s.append("Interest Income : ").append(String.format("$%.2f", getInterestIncome())).append("\n");
		s.append("Amount of Tax : ").append(String.format("$%5.2f", getTaxAmount())).append("\n");
		return s.toString();
	}

	/**
	 * override withdraw from Account
	 * @return false
	 */
	public boolean withdraw(double amount) {
		return false;
	}

	/**
	 * override deposit from Account
	 */
	public void deposit( double amount ) {

	}

	/**
	 * override getBalance from account
	 * @return balanceAtMaturity
	 */
	public double getBalance() {
		return getBalanceAtMaturity();
	}

}

