package com.tbea.ic.operation.controller.servlet.Server;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbea.ic.operation.controller.webservice.account.ServiceAccount;
import com.tbea.ic.operation.controller.webservice.entry.IndicatorEntry;
import com.tbea.ic.operation.controller.webservice.indicatorstatus.IndicatorStatusQuery;


@Controller
@RequestMapping(value = "serviceServer")
public class ServiceServer {

	@Autowired
	private IndicatorStatusQuery query;
	
	@Autowired
	private ServiceAccount account;
	
	@Autowired
	private IndicatorEntry entry;
	
	@Autowired
	public void start(){
	    Endpoint.publish("http://localhost:9000/BusinessManagement/IndicatorStatusQuery", query);
	    Endpoint.publish("http://localhost:9000/BusinessManagement/ServiceAccount", account);
	    Endpoint.publish("http://localhost:9000/BusinessManagement/IndicatorEntry", entry);
	}
}
