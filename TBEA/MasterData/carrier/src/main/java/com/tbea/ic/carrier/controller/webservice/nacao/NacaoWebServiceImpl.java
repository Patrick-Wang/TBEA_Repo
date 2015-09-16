package com.tbea.ic.carrier.controller.webservice.nacao;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.carrier.model.entity.Organization;
import com.tbea.ic.carrier.service.nacao.NacaoService;

@Controller
@WebService
public class NacaoWebServiceImpl implements NacaoWebService {

	@Autowired
	NacaoService nacaoService;

	public List<Organization> queryByName(String compName) {
		return nacaoService.findByNameExactly(compName);
	}

}  