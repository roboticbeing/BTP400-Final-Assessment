package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import com.seneca.accounts.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Jessica Krishtul
 *
 */

class AccountTest {

	private Account account;
	
	/**
	 * test for null/negative values in account constructor
	 */
	@Test
	void testAccountConstructor() {
		account = new Account(null, null, -1);
		assertEquals(account.getBalance(), 0);
		assertEquals(account.getAccountNumber(), "");
		assertEquals(account.getFullName(), "");
	}

	/**
	 * test get balance method in account
	 */
	@Test
	void testGetBalance() {
		account = new Account("Jessica Krishtul", "SDJ8JD", 9000.01);
		assertEquals(account.getBalance(), 9000.01);
	}
}
