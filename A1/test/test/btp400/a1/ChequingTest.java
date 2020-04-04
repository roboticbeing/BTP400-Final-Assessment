/**
 * @author Jeasung
 */
package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import com.seneca.accounts.*;

import org.junit.jupiter.api.Test;

class ChequingTest {

	private Chequing c1, c2;

	/**
	 * test constructor for negative values
	 */
	@Test
	void testChequingConstructor() {
		c1 = new Chequing(null, null, 231.34, -1, -1);
		c2 = new Chequing("Jeasung", "30jd3", 500.00, 0, 0);
		assertEquals(c1, c2);
	}
	
	/**
	 * test balance if correct
	 */
	@Test
	void testGetBalance() {
		c1 = new Chequing("Jessica", "hjkdaha", 500, 0.9, 15);
		c1.withdraw(10);
		c1.deposit(100);
		assertEquals(90,c1.getBalance());
	}

	/**
	 * test withdraw
	 */
	@Test
	void testWithdraw() {
		c1 = new Chequing("Jessica", "dn3un", 500, 0.9, 15);
		boolean test = c1.withdraw(500);
		assertEquals(true, test);
		test= c1.withdraw(500);
		assertEquals(false, test);
		test = c1.withdraw(-50);
		assertEquals(false, test);
		
		double totalCharges =  c1.getTotalCharges();
		assertEquals(0.5, totalCharges);
		
		c1.withdraw(200);
		test = c1.withdraw(20);
		assertEquals(false, test);

		assertEquals(500 - 500 - 50 - c1.getTotalCharges(), c1.getBalance());
	}

	@Test
	void testDeposit() {
		c2 = new Chequing("Jeasung", "ffh98", 499, 0.2, 22);
		c2.deposit(-123);
		assertEquals(78, c2.getBalance());
		c2.deposit(9);
		assertEquals(78 + 9 - c2.getTotalCharges(),c2.getBalance());
	}

	

}
