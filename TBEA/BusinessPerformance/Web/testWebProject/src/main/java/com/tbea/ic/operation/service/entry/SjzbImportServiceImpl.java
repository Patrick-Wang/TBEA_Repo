package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.script.util.StringUtil;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.dl.sjzb.DlSjzbDao;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.xl.sjzb.XlSjzbDao;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.report.HBWebService;

import net.sf.json.JSONArray;



@Service
@Transactional("transactionManager")
public class SjzbImportServiceImpl implements SjzbImportService{


	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Autowired
	DlSjzbDao dlsjzbDao;
	
	@Autowired
	XlSjzbDao xlsjzbDao;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;


	@Override
	public Map<Company, JSONArray> getHBSjzb(Date d) {
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("company_name");
		cols.add("index_name");
		cols.add("sjs");
		List<Object[]> result = hbws.getHBSjzb(cols,d);
		Map<Company, JSONArray> retData = new HashMap<Company, JSONArray>();
		for (Object[] row : result){
			if ("应收账款总计".equals((String) row[1]) && "特变电工衡阳变压器有限公司".equals((String) row[0])){
				row[1] = "应收账款";
			}
			if ("其中：制造业国际签约".equals(StringUtil.trim((String) row[1]))){
				row[1] = "其中：制造业国际签约(万美元）";
			}
			parseRow(retData, row);
		}
		return retData;
	}


	
	private void parseRow(Map<Company, JSONArray> retData, Object[] row) {
		DWXX dwxx = dwxxDao.getByName((String) row[0]);
		if (null == dwxx){
			LoggerFactory.getLogger("WEBSERVICE").info("Sjzb import dwxx 不存在 : " + row[0]);
			return;
		}
		ZBXX zbxx = zbxxDao.getZbByName((String) row[1]);
		if (null == zbxx){
			LoggerFactory.getLogger("WEBSERVICE").info("Sjzb import zbxx 不存在 : " + row[1]);
			return;
		}	
		Company comp = companyManager.getBMDBOrganization().getCompanyById(dwxx.getId());
		JSONArray ja = new JSONArray();
			ja.add("" + zbxx.getId());
			ja.add(row[2] != null ? row[2].toString().replaceAll(",", "") : null);
		if (!retData.containsKey(comp)){
			retData.put(comp, new JSONArray());
		}
		retData.get(comp).add(ja);
	}

	private Map<Company, JSONArray> getSjzb(List<Object[]> sjzb){
		Map<Company, JSONArray> retData = new HashMap<Company, JSONArray>();
		for (Object[] row : sjzb){
			if ("其中：制造业国际签约".equals(StringUtil.trim((String) row[1]))){
				row[1] = "其中：制造业国际签约(万美元）";
			}
			parseRow(retData, row);
		}
		return retData;
	}
	
	@Override
	public Map<Company, JSONArray> getDLSjzb(Date d) {
		return getSjzb( dlsjzbDao.getSjzb(d));
	}

	@Override
	public Map<Company, JSONArray> getXLSjzb(Date d) {
		return getSjzb( xlsjzbDao.getSjzb(d));
	}


}
