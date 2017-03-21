package com.tbea.ic.carrier.controller.webservice.personInfo;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.carrier.service.person.PersonService;

@Controller
@WebService
public class PersonPushServiceImpl implements MDMPushService {

	@Autowired
	PersonService personService;

	@Override
	public String push(String info) {
		// TODO Auto-generated method stub
		return null;
	}

} 