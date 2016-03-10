package com.tbea.ic.carrier.controller.webservice.person;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.carrier.model.entity.Psn;
import com.tbea.ic.carrier.service.person.PersonService;

@Controller
@WebService
public class PersonWebServiceImpl implements PersonWebService {

	@Autowired
	PersonService personService;

	public int queryPersonInfoPagesCount() {
		return personService.queryPersonInfoPagesCount();
	}
	
	public List<Psn> queryPersonInfo(int pageIndex) {
		return personService.queryPersonInfo(pageIndex);
	}

	public List<Psn> queryPersonInfoById(String id) {
		return personService.queryPersonInfoById(id);
	}

	public String queryPersonNoById(String id) {
		return personService.queryPersonNoById(id);
	}
	   
} 