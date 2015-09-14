package com.tbea.ic.carrier.webservice;
import javax.jws.WebService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@WebService
@Transactional("transactionManager")
public class HelloWorldImpl implements HelloWorld {

	public String sayHi(String who) {
		// TODO Auto-generated method stub
		return null;
	}  

}  