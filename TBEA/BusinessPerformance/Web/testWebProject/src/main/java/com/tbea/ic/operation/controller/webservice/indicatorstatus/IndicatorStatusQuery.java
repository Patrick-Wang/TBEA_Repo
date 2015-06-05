package com.tbea.ic.operation.controller.webservice.indicatorstatus;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;

@WebService
public interface IndicatorStatusQuery {
	List<ZBStatus> getStatus(
			@WebParam(name = "usr")
			ServiceUser usr, 
			@WebParam(name = "type")
			ZBType type, 
			@WebParam(name = "date")
			Date date, 
			@WebParam(name = "comps")
			List<Integer> comps);
}
