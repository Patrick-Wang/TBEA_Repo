package com.tbea.ic.operation.controller.webservice.entry;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;
@WebService
public interface IndicatorEntry {
	void save(
			@WebParam(name = "usr")
			ServiceUser usr, 
			@WebParam(name = "compId")
			Integer compId,
			@WebParam(name = "type")
			ZBType type, 
			@WebParam(name = "date")
			Date date, 
			@WebParam(name = "indicators")
			List<Indicator> indicators);
	
	void submit(
			@WebParam(name = "usr")
			ServiceUser usr, 
			@WebParam(name = "compId")
			Integer compId,
			@WebParam(name = "type")
			ZBType type, 
			@WebParam(name = "date")
			Date date, 
			@WebParam(name = "indicators")
			List<Indicator> indicators);
	
	void submit2Deputy(
			@WebParam(name = "usr")
			ServiceUser usr, 
			@WebParam(name = "compId")
			Integer compId,
			@WebParam(name = "type")
			ZBType type, 
			@WebParam(name = "date")
			Date date, 
			@WebParam(name = "indicators")
			List<Indicator> indicators);
}
