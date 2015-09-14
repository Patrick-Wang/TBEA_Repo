package com.tbea.ic.carrier.controller.webservice.nacao;
import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;

import com.tbea.ic.carrier.model.entity.Organization;

@WebService
public interface NacaoWebService {  

   public List<Organization> queryByName(String compName);  
     
}  