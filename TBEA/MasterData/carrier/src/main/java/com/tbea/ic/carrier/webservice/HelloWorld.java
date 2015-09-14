package com.tbea.ic.carrier.webservice;
import javax.jws.WebService;
import javax.jws.WebMethod;
@WebService
public interface HelloWorld {  

   public String sayHi(String who);  
     
}  