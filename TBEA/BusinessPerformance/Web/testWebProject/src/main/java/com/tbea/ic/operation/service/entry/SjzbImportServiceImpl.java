package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.indi.relation.Relationships;
import com.tbea.ic.operation.model.dao.exchangeRate.ExchangeRateDao;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.ExchangeRate;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.QXGL;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.entry.ZBListenerAggregator.IndiValues;
import com.tbea.ic.operation.service.entry.zbCalculator.GeneralZbCalculator;
import com.tbea.ic.operation.service.entry.zbCalculator.NdjhZbCalculator;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbCalculator.ZbCalculator;
import com.tbea.ic.operation.service.entry.zbInjector.SimpleZbInjectorFactory;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.service.report.HBWebService;



@Service
@Transactional("transactionManager")
public class SjzbImportServiceImpl implements SjzbImportService{


	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	ZBXXDao zbxxDao;
	

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;


	@Override
	public Map<Company, JSONArray> getHBSjzb(Date d) {
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("company_name");
		cols.add("index_name");
		cols.add("sjs");
		List<List<Object>> result = hbws.getHBSjzb(cols,d);
		Map<Company, JSONArray> retData = new HashMap<Company, JSONArray>();
		for (List<Object> row : result){
			parseRow(retData, row);
		}
		return retData;
	}


	private void parseRow(Map<Company, JSONArray> retData, List<Object> row) {
		DWXX dwxx = dwxxDao.getByName((String) row.get(0));
		if (null == dwxx){
			LoggerFactory.getLogger("WEBSERVICE").info("Sjzb import dwxx 不存在 : " + row.get(0));
			return;
		}
		ZBXX zbxx = zbxxDao.getZbByName((String) row.get(1));
		if (null == zbxx){
			LoggerFactory.getLogger("WEBSERVICE").info("Sjzb import zbxx 不存在 : " + row.get(1));
			return;
		}	
		Company comp = companyManager.getBMDBOrganization().getCompany(dwxx.getId());
		JSONArray ja = new JSONArray();
			ja.add("" + zbxx.getId());
			ja.add(((String) row.get(2)).replaceAll(",", ""));
		if (!retData.containsKey(comp)){
			retData.put(comp, new JSONArray());
		}
		retData.get(comp).add(ja);
	}


}
