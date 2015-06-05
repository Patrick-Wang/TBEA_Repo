package com.tbea.ic.operation.controller.webservice.entry;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;
@WebService
public interface IndicatorEntry {
	void save(ServiceUser usr, Integer compId, ZBType type, Date date, List<Indicator> indicators);
	void submit(ServiceUser usr, Integer compId, ZBType type, Date date, List<Indicator> indicators);
	void submit2Deputy(ServiceUser usr, Integer compId, ZBType type, Date date, List<Indicator> indicators);
}
