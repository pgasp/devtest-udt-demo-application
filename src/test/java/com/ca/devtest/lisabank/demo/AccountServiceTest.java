/**
 * 
 */
package com.ca.devtest.lisabank.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.devtest.lisabank.demo.business.BankService;
import com.ca.devtest.lisabank.wsdl.Account;

/**
 * @author gaspa03
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LisaBankClientApplication.class)
public class AccountServiceTest {
	@Autowired
	private BankService bankServices;
	private String token = null;

	
	
	@Test
	public void createUserWithCheckingAccount() {

		// Given
		String user = "pascal";
		String password = "password";
		int amount = 1000;
		// prepare context
		bankServices.deleteUser(user);
		// When
		Account account = bankServices.createUserWithCheckingAccount(user, password, amount);
		// Then
		assertNotNull(account);
		assertEquals("Le balance du compte n'est pas conforme",amount, account.getBalance().intValue());
	}
}
