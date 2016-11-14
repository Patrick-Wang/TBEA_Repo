package com.tbea.ic.operation.service.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;

import com.apex.livebos.ws.ColInfo;
import com.apex.livebos.ws.LBEBusinessService;
import com.apex.livebos.ws.LBEBusinessWebService;
import com.apex.livebos.ws.LbMetaData;
import com.apex.livebos.ws.LbParameter;
import com.apex.livebos.ws.LbRecord;
import com.apex.livebos.ws.LoginResult;
import com.apex.livebos.ws.QueryOption;
import com.apex.livebos.ws.QueryResult;
import com.apex.livebos.ws.ValueOption;

public class HBWebService {

	LBEBusinessWebService service;
	LBEBusinessService client;

	private boolean parseQuery(QueryResult queryResult, List<String> cols,
			List<Object[]> result) {
		LoggerFactory.getLogger("WEBSERVICE").info("Query result message : " + queryResult.getMessage());
		LbMetaData lbMetaData = queryResult.getMetaData();
		List<ColInfo> colInfoList = lbMetaData.getColInfo();
		List<LbRecord> lbRecordList = queryResult.getRecords();
		List<Object> valueList = null;
		int index = -1;
		for (LbRecord lbRecord : lbRecordList) {
			valueList = lbRecord.getValues();
			LoggerFactory.getLogger("WEBSERVICE").info(
					JSONArray.fromObject(valueList).toString());
			if (cols.isEmpty()) {
				result.add(valueList.toArray());
			} else {
				Object[] record = new Object[cols.size()];
				for (int i = 0; i < colInfoList.size(); ++i) {
					index = cols.indexOf(colInfoList.get(i).getLabel());
					if (index >= 0) {
						record[index] = valueList.get(i);
					}
				}
				LoggerFactory.getLogger("WEBSERVICE").info(
						JSONArray.fromObject(record).toString());
				result.add(record);
			}
		}
		return queryResult.isHasMore();
	}

	private LoginResult login(String api) {
		if (client == null){
			service = new LBEBusinessWebService();
			client = service.getLBEBusinessServiceImplPort();
		}
		
		return client.login("web_test", "123456", api, "plain", "");
	}

	public List<Object[]> getHBData(List<String> cols, String schema, Date d) {
		LoginResult loginResult = login(schema);

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    //设置日历中月份的最大天数
	    cal.set(Calendar.DAY_OF_MONTH, lastDay);
		d = new Date(cal.getTimeInMillis());
		// // query
		int pageNum = 1;
		QueryOption queryOption = new QueryOption();
		queryOption.setValueOption(ValueOption.DISPLAY);
		queryOption.setQueryCount(false);
		queryOption.setBatchNo(pageNum++);
		queryOption.setBatchSize(1000);
		//
		List<LbParameter> lbParameterList = new ArrayList<LbParameter>();
		LbParameter lbParameter = new LbParameter();
		lbParameter.setName("P1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lbParameter.setValue(sdf.format(d));
		lbParameterList.add(lbParameter);
		QueryResult queryResult = client.query(loginResult.getSessionId(),
				schema, lbParameterList, "", queryOption);
		
		LbMetaData lbMetaData = queryResult.getMetaData();
		List<ColInfo> colInfoList = lbMetaData.getColInfo();
		if (LoggerFactory.getLogger("WEBSERVICE").isDebugEnabled()) {
			String title = "";
			for (int i = 0; i < colInfoList.size(); ++i) {
				title += colInfoList.get(i).getLabel() + "\t";
			}
			LoggerFactory.getLogger("WEBSERVICE").debug(title);
		}
		//
		List<Object[]> result = new ArrayList<Object[]>();
		while (parseQuery(queryResult, cols, result)) {
			queryOption.setBatchNo(pageNum++);
			queryResult = client.query(loginResult.getSessionId(), schema,
					lbParameterList, "", queryOption);
		}

		// logout
		client.logout(loginResult.getSessionId());
		return result;
	}

	public List<Object[]> getHBNbzlqk(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBNbzlqk");
		return getHBData(cols, "nbzlwt_jkSql", new Date());
	}

	public List<Object[]> getHBWbzlqk(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBWbzlqk");
		return getHBData(cols, "wbzlwt_jkSql497", new Date());
	}

	// 衡变实际指标
	public List<Object[]> getHBSjzb(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBSjzb");
		return getHBData(cols, "jygk_sjzb_hb_sql", new Date());
	}

	public List<Object[]> getHBSjzb(List<String> cols, Date date) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBSjzb");
		return getHBData(cols, "jygk_sjzb_hb_sql", date);
	}

	// 输变电产量完成情况
	public List<Object[]> getHBClwcqk(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBClwcqk");
		return getHBData(cols, "jygk_clwc_qk_Sql", new Date());
	}

	public List<Object[]> getHBClwcqk(List<String> cols, Date date) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBClwcqk");
		return getHBData(cols, "jygk_clwc_qk_Sql", date);
	}

	// 输变电细分产品签约
	public List<Object[]> getHBCpqy(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBCpqy");
		return getHBData(cols, "jykg_cpqy_xf_Sql", new Date());
	}

	public List<Object[]> getHBCpqy(List<String> cols, Date date) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBCpqy");
		return getHBData(cols, "jykg_cpqy_xf_Sql", date);
	}

	// 输变电细分市场签约
	public List<Object[]> getHBScqy(List<String> cols) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBScqy");
		return getHBData(cols, "jykg_scqy_xf_Sql", new Date());
	}

	public List<Object[]> getHBScqy(List<String> cols, Date date) {
		LoggerFactory.getLogger("WEBSERVICE").info("getHBScqy");
		return getHBData(cols, "jykg_scqy_xf_Sql", date);
	}

}
