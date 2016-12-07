package com.tbea.ic.operation.controller.servlet.report.handlers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Company15Code;
import com.tbea.ic.operation.common.CompanyNCCode;
import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.PropMap;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.report.Arrays;
import com.tbea.ic.operation.controller.servlet.report.CompanyTypeHelper;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.controller.servlet.report.Counter;
import com.tbea.ic.operation.controller.servlet.report.GroupSum;
import com.tbea.ic.operation.controller.servlet.report.ReportMath;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.report.HBWebService;
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
	ApproveService approveService;
	
	@Autowired
	private EntryService entryService;
	
	private SJZBImporter sjImporter;
	
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		sjImporter = new SJZBImporter(companyManager, approveService, entryService);
	}

	
	
	@Override
	public void onHandle(Context context) {
		context.put("groupSum", new GroupSum());
		context.put("array", new Arrays());
		context.put("accFactory", accFac);
		context.put("compMgr", companyManager);
		context.put("counterFactory", new Counter());
		context.put("zlqkWebService", new HBWebService());
		context.put(com.tbea.ic.operation.reportframe.component.Component.CALENDAR, new EasyCalendar());
		context.put("CompanyType", new PropMap(){

			@Override
			public Object getProperty(Object key) throws Exception {
				return CompanyType.valueOf((String)key);
			}
			
		});
		
		context.put("GSZB", new PropMap(){

			@Override
			public Object getProperty(Object key) throws Exception {
				return GSZB.valueOf((String)key);
			}
			
		});
		
		context.put("sjImporter", sjImporter);
		
		context.put("companyTypeHelper", new CompanyTypeHelper());
		
		
		context.put("NCCode", new CompanyNCCode());
		context.put("Code15", new Company15Code());
		context.put("math", new ReportMath());
	}

}
