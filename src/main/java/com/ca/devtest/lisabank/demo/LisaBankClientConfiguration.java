package com.ca.devtest.lisabank.demo;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ca.devtest.lisabank.wsdl.EJB3AccountControlBean;
import com.ca.devtest.lisabank.wsdl.EJB3UserControlBean;

/**
 * @author gaspa03
 *
 */
@Configuration
public class LisaBankClientConfiguration {
	   @Value("${webservice.url.user}")
	    private String userServiceAddress;
	   @Value("${webservice.url.account}")
	    private String accountServiceAddress;
	@Bean
	public EJB3UserControlBean userServiceClient() {

		JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
		jaxWsProxyFactory.setServiceClass(EJB3UserControlBean.class);
		jaxWsProxyFactory.setAddress(userServiceAddress);
		return (EJB3UserControlBean) jaxWsProxyFactory.create();

	}
	@Bean
	public EJB3AccountControlBean accountServiceClient() {

		JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
		jaxWsProxyFactory.setServiceClass(EJB3AccountControlBean.class);
		jaxWsProxyFactory.setAddress(accountServiceAddress);
		return (EJB3AccountControlBean) jaxWsProxyFactory.create();

	}
}
