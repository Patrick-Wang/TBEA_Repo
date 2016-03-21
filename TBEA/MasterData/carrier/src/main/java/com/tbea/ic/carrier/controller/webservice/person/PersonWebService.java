package com.tbea.ic.carrier.controller.webservice.person;
import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;

import com.tbea.ic.carrier.model.entity.Psn;

@WebService
public interface PersonWebService {  
   
	public int queryPersonInfoPagesCount();

	public List<Psn> queryPersonInfo(int pageIndex);

	public List<Psn> queryPersonInfoById(String id);

	public String queryPersonNoById(String id);
	
	public String queryPersonSSOById(String id);
}  