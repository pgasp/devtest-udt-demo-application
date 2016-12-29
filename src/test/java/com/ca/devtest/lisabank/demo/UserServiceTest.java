package com.ca.devtest.lisabank.demo;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.devtest.lisabank.demo.business.BankService;
import com.ca.devtest.lisabank.wsdl.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=LisaBankClientApplication.class)

public class UserServiceTest {

    @Autowired
    private BankService bankServices;
    
    @Test
    public void getListUser()   {
        // Given
        
        // When
        List<User> users= bankServices.getListUserWithoutAdmin();
         // Then
        assertNotNull(users);
        assertEquals("Il y a plus de 7 utilisateurs, le webservice \"userControl\" ne sont pas correctement configuré",6,users.size());
        assertNotEquals("Admin",users.get(0).getLname());
       
    }
}
