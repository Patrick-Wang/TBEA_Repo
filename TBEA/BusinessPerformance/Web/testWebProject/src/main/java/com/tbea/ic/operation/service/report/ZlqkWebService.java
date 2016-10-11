package com.tbea.ic.operation.service.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.component.entity.Context;

public class ZlqkWebService {

	Context context;
	
	
	private boolean parseQuery(QueryResult queryResult, List<String> cols, List<List<Object>> result){
		LbMetaData lbMetaData = queryResult.getMetaData();
		List<ColInfo> colInfoList = lbMetaData.getColInfo();
		List<LbRecord> lbRecordList = queryResult.getRecords();
		List<Object> valueList = null;
		int index = -1;
		for (LbRecord lbRecord : lbRecordList) {
			valueList = lbRecord.getValues();
			List<Object> record = Util.resize(new ArrayList<Object>(), cols.size());
			for (int i = 0; i < colInfoList.size(); ++i){
				index = cols.indexOf(colInfoList.get(i).getLabel());
				if (index >= 0){
					record.set(index, valueList.get(i));				
				}
			}
			result.add(record);
		}
		return queryResult.isHasMore();
	}
	
	public List<List<Object>> getHBNbzlqk(List<String> cols){
		LBEBusinessWebService service = new LBEBusinessWebService();
		LBEBusinessService client = service.getLBEBusinessServiceImplPort();
		LoginResult loginResult = client.login("web_test", "123456", "nbzlwt_jkSql", "plain", "");

//		// query
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
		lbParameter.setValue(sdf.format(new Date()));
		lbParameterList.add(lbParameter);
		QueryResult queryResult = client.query(loginResult.getSessionId(),
				"nbzlwt_jkSql", lbParameterList, "", queryOption);
		List<List<Object>> result = new ArrayList<List<Object>>(); 
		while (parseQuery(queryResult, cols, result)){
			queryOption.setBatchNo(pageNum++);
			queryResult = client.query(loginResult.getSessionId(),
					"nbzlwt_jkSql", lbParameterList, "", queryOption);
		}

		// logout
		client.logout(loginResult.getSessionId());
		return result;
	}
	
	public List<List<Object>> getHBWbzlqk(List<String> cols){
		LBEBusinessWebService service = new LBEBusinessWebService();
		LBEBusinessService client = service.getLBEBusinessServiceImplPort();
		LoginResult loginResult = client.login("web_test", "123456", "wbzlwt_jkSql497", "plain", "");

//		// query
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
		lbParameter.setValue(sdf.format(new Date()));
		lbParameterList.add(lbParameter);
		QueryResult queryResult = client.query(loginResult.getSessionId(),
				"wbzlwt_jkSql497", lbParameterList, "", queryOption);
//
		List<List<Object>> result = new ArrayList<List<Object>>(); 
		while (parseQuery(queryResult, cols, result)){
			queryOption.setBatchNo(pageNum++);
			queryResult = client.query(loginResult.getSessionId(),
					"wbzlwt_jkSql497", lbParameterList, "", queryOption);
		}


		// logout
		client.logout(loginResult.getSessionId());
		return result;
	}
}
