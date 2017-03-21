package com.tbea.ic.carrier.controller.webservice.personInfo;
import javax.jws.WebService;

@WebService
public interface MDMPushService {  
 
	public String push(String info);
}  