package com.tbea.ic.operation.controller.servlet.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpServer;
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
	
	private List<Endpoint> endpoints = new ArrayList<Endpoint>();
	

	@Autowired
	public void start() throws IOException{
		
		HttpServer hs = HttpServer.create(new InetSocketAddress(9000), 0);
		com.sun.net.httpserver.HttpContext context = hs.createContext("/BusinessManagement/IndicatorStatusQuery");
		//context.setAuthenticator(new ServiceAuthenticator());

		endpoints.add(Endpoint.create(query));
		endpoints.add(Endpoint.create(account));
		endpoints.add(Endpoint.create(entry));
		//endpoints.add(Endpoint.create(account));
		
		//endpoints.get(0).publish(context);
		
		//context = hs.createContext("/BusinessManagement/ServiceAccount");
		//context.setAuthenticator(new ServiceAuthenticator());
		hs.start();
		
		endpoints.get(0).publish(context);
		
		context = hs.createContext("/BusinessManagement/ServiceAccount");
		endpoints.get(1).publish(context);
		
		context = hs.createContext("/BusinessManagement/IndicatorEntry");
		endpoints.get(2).publish(context);
		
///		endpoints.add(Endpoint.publish("http://localhost:9000/BusinessManagement/IndicatorStatusQuery", query));
//		endpoints.add(Endpoint.publish("http://localhost:9000/BusinessManagement/ServiceAccount", account));
//		endpoints.add(Endpoint.publish("http://localhost:9000/BusinessManagement/IndicatorEntry", entry));
//		endpoints.get(0).publish(context);
	}
}
