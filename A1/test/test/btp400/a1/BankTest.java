/**
 * @author Jessica Krishtul
 */

package test.btp400.a1;

import com.seneca.business.Bank;

import com.seneca.accounts.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankTest {

	private Bank bank;	

	

	/**
	 * search by balance test
	 */
	@Test
	void testSearchByBalance() {
		bank = new Bank("TD");
		Account a1 = new GIC("Jessica", "ndjsnf", 9000, 1, 0.01);
		Account a2 = new GIC("Jeasung", "dj3j", 100, 2, 0.05);
		bank.addAccount(a1);
		bank.addAccount(a2);
		Account[] account = new Account[2];
		account[0] = a1;
		account[1] = a2;
		Account[] test = bank.searchByBalance(a1.getBalance());
		assertEquals(account[0], test[0]);
		assertEquals(account[1], test[1]);
	}

	/**
	 * search by account name test
	 */
	@Test
	void testSearchByAccountName() {
		bank = new Bank("RBC");
		Account a1 = new Chequing("Jessica", "adjkhs", 9000, 0.2, 1);
		Account a2 = new Chequing("Jeasung", "sadkj", 300, 0.1, 5);
		bank.addAccount(a1);
		bank.addAccount(a2);
		Account[] account = new Account[2];
		account [0] = a1;
		account [1] = a2;
		Account[] test = bank.searchByAccountName("Minh");
		assertEquals(account [0], test[0]);
		assertEquals(account [1], test[1]);
	}


}
