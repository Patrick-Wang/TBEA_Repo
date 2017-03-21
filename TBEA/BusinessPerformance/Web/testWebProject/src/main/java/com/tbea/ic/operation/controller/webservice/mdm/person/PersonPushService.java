package com.tbea.ic.operation.controller.webservice.mdm.person;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tbea.ic.operation.model.entity.hr.Employee;
import com.tbea.mdm.ws.MDMResponse;

@WebService
public interface PersonPushService {  
 
	public MDMResponse push(@WebParam(name="batch") String batch, @WebParam(name="employees") List<Employee> employees);
}