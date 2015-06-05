package com.tbea.ic.operation.controller.webservice.indicatorstatus;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;

@WebService
public interface IndicatorStatusQuery {
	List<ZBStatus> getStatus(ServiceUser usr, ZBType type, Date date, List<Integer> comps);
}
