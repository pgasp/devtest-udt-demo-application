/**
 * 
 */
package com.ca.devtest.lisabank.demo.business;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ca.devtest.lisabank.wsdl.Account;
import com.ca.devtest.lisabank.wsdl.AccountType;
import com.ca.devtest.lisabank.wsdl.EJB3AccountControlBean;
import com.ca.devtest.lisabank.wsdl.EJB3UserControlBean;
import com.ca.devtest.lisabank.wsdl.TokenBean;
import com.ca.devtest.lisabank.wsdl.User;

/**
 * @author gaspa03
 *
 */
@Component
public class LisaBankService implements BankService {
	 @Autowired
	    private EJB3UserControlBean userControlBean;
	 @Autowired
	 	private EJB3AccountControlBean accountControlBean;
	 
	 @Autowired
	 	private TokenBean tokenBean;
	 
	 /* (non-Javadoc)
	 * @see com.ca.devtest.lisabank.demo.business.BankService#createUserWithCheckingAccount(java.lang.String, java.lang.String, int)
	 */
	@Override
	public Account createUserWithCheckingAccount(String username, String password, int amount){
		 Account account=null;
		 if(null==userControlBean.getUser(username)){
			 User user=userControlBean.addUser(username, password);
			  account= new Account();
			 account.setBalance(new BigDecimal(amount));
			 account.setType(AccountType.CHECKING);
			 	
			 String accountId=accountControlBean.addAccount(user.getLogin(), account);
			 account=accountControlBean.getAccount(accountId);
		 }
		 
		 return account;
	 }
	 
	public boolean deleteUser(String username){
		
		boolean result=false;
		 if(null!=userControlBean.getUser(username)){
			result=userControlBean.deleteUser(username);
			
		 }
		 
		 return result;
	 }
	 /* (non-Javadoc)
	 * @see com.ca.devtest.lisabank.demo.business.BankService#getListUserWithoutAdmin()
	 */
	@Override
	public  List<User> getListUserWithoutAdmin(){
		
		List<User> users= userControlBean.listUsers();
		users.remove(0);
		return users;
	 }
	
	public String authenticate(String userName, String password){
		
		return tokenBean.getNewToken(userName, password);
		
	}
}
