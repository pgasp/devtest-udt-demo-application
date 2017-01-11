package com.ca.devtest.lisabank.demo.sv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.devtest.lisabank.demo.LisaBankClientApplication;
import com.ca.devtest.lisabank.demo.business.BankService;
import com.ca.devtest.lisabank.wsdl.User;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServer;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualService;
import com.ca.devtest.sv.devtools.annotation.Protocol;
import com.ca.devtest.sv.devtools.annotation.ProtocolType;
import com.ca.devtest.sv.devtools.junit.VirtualServicesRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LisaBankClientApplication.class)
@DevTestVirtualServer(deployServiceToVse = "VSE")
public class UserServiceTest {

	@Autowired
	private BankService bankServices;
	@Rule
	public VirtualServicesRule rules = new VirtualServicesRule();
	
	@DevTestVirtualService(serviceName = "UserServiceTest-EJB3UserControlBean", port = 9081, basePath = "/itkoExamples/EJB3UserControlBean", rrpairsFolder = "UserServiceTest/getListUser/EJB3UserControlBean", requestDataProtocol = {
			@Protocol(ProtocolType.DPH_SOAP) })
	@Test
	public void getListUser() {
		// Given

		// When
		List<User> users = bankServices.getListUserWithoutAdmin();
		// Then
		assertNotNull(users);
		assertEquals("Il y a plus de 8 utilisateurs, le webservice \"userControl\" n'est pas correctement configuré",
				8, users.size());
		assertNotEquals("Admin", users.get(0).getLname());

	}
}
