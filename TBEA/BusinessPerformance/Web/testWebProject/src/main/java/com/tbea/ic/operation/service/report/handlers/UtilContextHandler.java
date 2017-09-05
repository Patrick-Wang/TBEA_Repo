package com.tbea.ic.operation.service.report.handlers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.script.maker.CodeMaker;
import com.frame.script.util.PropMap;
import com.tbea.ic.operation.common.Company15Code;
import com.tbea.ic.operation.common.CompanyJjzjzCode;
import com.tbea.ic.operation.common.CompanyNCCode;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.report.Arrays;
import com.tbea.ic.operation.controller.servlet.report.CompanyTypeHelper;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.controller.servlet.report.Counter;
import com.tbea.ic.operation.controller.servlet.report.GroupSum;
import com.tbea.ic.operation.controller.servlet.report.LoggerProxy;
import com.tbea.ic.operation.controller.servlet.report.ReportMath;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.report.ComponentManagerServiceImpl;
import com.tbea.ic.operation.service.report.HBWebService;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.xml.frame.report.component.Component;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.EasyCalendar;

@org.springframework.stereotype.Component(UtilContextHandler.NAME)
public class UtilContextHandler implements ContextHandler {

	public static final String NAME = "UtilContextHandler";

	protected static String templatePath;
	static {
		try {
			templatePath = new URI(ComponentManagerServiceImpl.class.getClassLoader().getResource("")
					.getPath()).getPath().substring(1)
					+ "META-INF/templates/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
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
		context.put("TEMPLATE", templatePath);
		context.put("cmaker", new CodeMaker());
		context.put("groupSum", new GroupSum());
		context.put("array", new Arrays());
		context.put("accFactory", accFac);
		context.put("compMgr", companyManager);
		context.put("counterFactory", new Counter());
		context.put("zlqkWebService", new HBWebService());
		context.put(Component.CALENDAR, new EasyCalendar());
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
		context.put("JjzjzCode", new CompanyJjzjzCode());
		context.put("math", new ReportMath());
		context.put("logger", new PropMap(){

			@Override
			public Object getProperty(Object key) throws Exception {
				return new LoggerProxy().getLogger((String) key);
			}
			
		});
		
		context.put("jydws", BMDepartmentDB.getJydw(companyManager));
	}

}
