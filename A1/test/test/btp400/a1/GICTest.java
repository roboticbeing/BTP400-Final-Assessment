/**
 * @author Jeasung
 */
package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import com.seneca.accounts.*;

import org.junit.jupiter.api.Test;

class GICTest {

	private GIC c1, c2;
	
	@Test
	void testGICConstructor() {
		c1 = new GIC(null, null, -1, -1, -0.01);
		c2 = new GIC(null, null, -1, -1, -0.01);
		
		assertEquals(c1, c2);
	}
	

	@Test
	void testWithdraw() {
		c1 = new GIC("Jeasung", "fff", 5000, 2, 0.0123);
		assertEquals(false, c1.withdraw(10));
		assertEquals(5000, c1.getBalance());
	}
	
	@Test
	void testDeposit() {
		c1 = new GIC("Jeasung", "fff", 5000, 2, 0.0123);
		c1.deposit(5000);
		assertEquals(5000, c1.getBalance());
	}
	
	@Test
	void testGetBalance() {
		c1 = new GIC("Jeasung", "fff", 5000, 2, 0.0123);
		double expected = new BigDecimal(0.0123).add(new BigDecimal(1)).pow(2).multiply(new BigDecimal(5000)).doubleValue();
		assertEquals(expected, c1.getBalance());
	}
	
	@Test
	void testGetBalance2() {
		c1= new GIC("Jeasung", "fff", 5000, 2, 0.0123);
		double expected = new BigDecimal(0.005).add(new BigDecimal(2)).pow(2).multiply(new BigDecimal(50000)).doubleValue();
		assertEquals(expected,c1.getBalance());
	}

}
