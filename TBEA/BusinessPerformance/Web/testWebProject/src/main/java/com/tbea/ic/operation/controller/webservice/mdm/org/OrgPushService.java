package com.tbea.ic.operation.controller.webservice.mdm.org;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tbea.ic.operation.model.entity.hr.Org;
import com.tbea.mdm.ws.MDMResponse;

@WebService
public interface OrgPushService {  
 
	public MDMResponse push(@WebParam(name="batch") String batch, @WebParam(name="orgs") List<Org> orgs);
}