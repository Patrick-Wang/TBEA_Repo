package com.tbea.datatransfer.service.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class WebServiceClient {

	public List<Map<String, Object>> getRec(String userid, String password,
			String scheme) {
		LBEBusinessWebService service = new LBEBusinessWebService();
		LBEBusinessService client = service.getLBEBusinessServiceImplPort();
		// login
		// LoginResult loginResult = client.login("web_test", "123456",
		// funcName,
		// "plain", "");
		LoginResult loginResult = client.login(userid, password, scheme,
				"plain", "");
		// query
		QueryOption queryOption = new QueryOption();
		queryOption.setValueOption(ValueOption.DISPLAY);
		queryOption.setQueryCount(true);
		queryOption.setBatchNo(1);
		queryOption.setBatchSize(100);

		List<LbParameter> lbParameterList = new ArrayList<LbParameter>();
		LbParameter lbParameter = new LbParameter();
		lbParameter.setName("P1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lbParameter.setValue(sdf.format(new Date()));
		lbParameterList.add(lbParameter);
		QueryResult queryResult = client.query(loginResult.getSessionId(),
				scheme, lbParameterList, "", queryOption);

		LbMetaData lbMetaData = queryResult.getMetaData();
		List<ColInfo> colInfoList = lbMetaData.getColInfo();
		List<LbRecord> lbRecordList = queryResult.getRecords();
		List<Object> valueList = null;
		List<Map<String, Object>> recList = new ArrayList<Map<String,Object>>();
		Map<String, Object> recMap = null;
		for (LbRecord lbRecord : lbRecordList) {
			valueList = lbRecord.getValues();
			recMap = new HashMap<String, Object>();
			for (int i = 0; i < colInfoList.size(); i++) {
				recMap.put(colInfoList.get(i).getLabel(), valueList.get(i));
			}
			recList.add(recMap);
		}

		// logout
		client.logout(loginResult.getSessionId());

		return recList;
	}
}
