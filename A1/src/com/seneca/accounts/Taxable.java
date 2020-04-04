/**
 * @author Jeasung
 */
package com.seneca.accounts;
import java.math.BigDecimal;

public interface Taxable {
	
	public void calculateTax();
	public double getTaxAmount();
	public String createTaxStatement();
	public static final BigDecimal taxRate = new BigDecimal(0.15);
	
}
