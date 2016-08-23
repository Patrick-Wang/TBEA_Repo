package com.tbea.ic.operation.controller.servlet.report.handlers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.report.Arrays;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.controller.servlet.report.GroupSum;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;

@Component(UtilContextHandler.NAME)
public class UtilContextHandler implements ContextHandler {

	public static final String NAME = "UtilContextHandler";

	@Autowired
	NDJHZBDao ndjhzbDao;

	@Autowired
	YDJHZBDao ydjhzbDao;

	@Autowired
	YDZBZTDao ydzbztDao;

	@Autowired
	SJZBDao sjzbDao;

	@Autowired
	YJ20ZBDao yj20zbDao;

	@Autowired
	YJ28ZBDao yj28zbDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	AccumulatorFactory accFac;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
	}

	@Override
	public void onHandle(Context context) {
		context.put("groupSum", new GroupSum());
		context.put("array", new Arrays());
		context.put("accFactory", accFac);
		context.put("compMgr", companyManager);
	}

}
