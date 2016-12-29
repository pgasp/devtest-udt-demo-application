package com.ca.devtest.lisabank.demo.business;

import java.util.List;

import com.ca.devtest.lisabank.wsdl.Account;
import com.ca.devtest.lisabank.wsdl.User;

public interface BankService {

	Account createUserWithCheckingAccount(String username, String password, int amount);

	/**
	 * @return List of User without Admin User
	 */
	List<User> getListUserWithoutAdmin();

	
	/**
	 * @param username
	 * @return
	 */
	public boolean deleteUser(String username);

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public String authenticate(String userName, String password);
	
}