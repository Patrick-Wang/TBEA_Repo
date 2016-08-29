package com.tbea.ic.operation.service.futures;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.Connection.DBConnectionManager;

@Service(FuturesServiceImpl.NAME)
@Transactional("transactionManager")
public class FuturesServiceImpl implements FuturesService {

	public final static String NAME = "FuturesServiceImpl";

	public final static String USERID = "103";

	public final static String QYBH_CU = "5;6;7;9;10;11";

	public final static List<String> QYMC_CU_LIST = new ArrayList<String>();

	static {
		QYMC_CU_LIST.add("沈变公司");
		QYMC_CU_LIST.add("衡变公司");
		QYMC_CU_LIST.add("新变厂");
		QYMC_CU_LIST.add("鲁缆公司");
		QYMC_CU_LIST.add("新缆厂");
		QYMC_CU_LIST.add("德缆公司");
	}

	public final static String QYBH_AL = "9;10;11";

	public final static List<String> QYMC_AL_LIST = new ArrayList<String>();

	static {
		QYMC_AL_LIST.add("鲁缆公司");
		QYMC_AL_LIST.add("新缆厂");
		QYMC_AL_LIST.add("德缆公司");
	}

	@Override
	public JSONArray getTableValues(String type) {
		List<QHMXBean> QHMXList = getValues(type);
		List<List<String>> resultList = new ArrayList<List<String>>();
		for (QHMXBean qhmxBean : QHMXList) {
			List<String> dataList = new ArrayList<String>();
			// dataList.add(qhmxBean.getDate());// 日期，图横坐标
			dataList.add(qhmxBean.getQymc());// 企业名称
			dataList.add(qhmxBean.getType());// 类型
			dataList.add(qhmxBean.getCcl());// 持仓量
			dataList.add(qhmxBean.getCcjj());// 持仓均价
			dataList.add(qhmxBean.getPrice());// 现价
			dataList.add(qhmxBean.getYk());// 盈亏比例
			dataList.add(qhmxBean.getYkje());// 盈亏金额
			resultList.add(dataList);
		}
		return JSONArray.fromObject(resultList);
	}

	@Override
	public JSONArray getChartValues(String type) {
		String companyId = null;
		String tempCompanyName = null;
		String tempDate = null;
		String profit_Lost_Amount = null;
		List<Double> tempValues = new ArrayList<Double>();
		Map<String, Object> dataMap = null;

		Set<String> dateSet = new TreeSet<String>();
		Map<String, String> valueMap = new HashMap<String, String>();

		List<QHMXBean> QHMXList = getValues(type);
		for (QHMXBean qhmxBean : QHMXList) {
			companyId = qhmxBean.getQybh();
			if (null != companyId && !"".equals(companyId)
					&& !"1000".equals(companyId)) {
				// qhmxBeanCuTotal = qhmxBean;
				// getAQ().id(R.id.profit_lost_webview_details).getTextView()
				// .setText("盈亏金额总计: " +
				// StringUtil.financeFormat(qhmxBean.getYkje()) + " 元");
				//
				// }

				tempCompanyName = qhmxBean.getQymc();
				tempDate = qhmxBean.getDate();
				dateSet.add(tempDate);
				profit_Lost_Amount = qhmxBean.getYkje();
				tempValues.add(Double.valueOf(profit_Lost_Amount));
				valueMap.put(tempCompanyName + tempDate, profit_Lost_Amount);
			}

		}

		// toolTipFormatter_Copper = new StringBuffer("期货利润 (元)");
		// int listSize = companyNames.size();
		// for (int i = 0; i < listSize; i++) {
		// toolTipFormatter_Copper.append("<br/>{a" + i + "} : {c" + i + "}");
		// }
		// xAxisArray = new JSONArray(dateSet);
		// tempMap = sortData(tempValues);
		// yAxisMin_Copper = tempMap.get("min");
		// yAxisMax_Copper = tempMap.get("max");

		List<JSONObject> dataObjects = new ArrayList<JSONObject>();

		String tempValue = null;
		List<String> valueList = new ArrayList<String>();
		List<String> companyNames = null;
		if (null != type && !"2".equals(type)) {
			companyNames = QYMC_CU_LIST;
		} else {
			companyNames = QYMC_AL_LIST;
		}
		for (String companyName : companyNames) {
			valueList.clear();
			for (String date : dateSet) {
				tempValue = valueMap.get(companyName + date);
				if (null != tempValue) {
					valueList.add(tempValue);
				} else {
					valueList.add("0");
				}
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("companyName", companyName);
			dataMap.put("dateSet", dateSet);
			dataMap.put("valueList", valueList);
			dataObjects.add(JSONObject.fromObject(dataMap));
			// blankObjects
			// .add(new JSONObject(
			// "{name : '"
			// + companyName
			// +
			// "', type : 'line', symbolSize: 0, itemStyle: {normal: {lineStyle: {width: 0}}},data : "
			// + blankList + "}"));
		}

		JSONArray dataArray = JSONArray.fromObject(dataObjects);
		// blankArray_Copper = new JSONArray(blankObjects);
		return dataArray;
	}

	private List<QHMXBean> getValues(String type) {
		DBConnectionManager manager = DBConnectionManager
				.getInstance("mobileSys");
		Connection conn = manager.getConnection("mobileSys");
		List<QHMXBean> QHMXList = new ArrayList<QHMXBean>();
		Statement stmt = null;
		ResultSet res = null;
		String companys = null;
		if (null != type && !"2".equals(type)) {
			companys = QYBH_CU;
		} else {
			companys = QYBH_AL;
		}
		Date today = new Date(System.currentTimeMillis());
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery("select * from dbo.f_tb2014_qhccmx("
					+ USERID + ",'" + companys + "'," + type + ",'" + today
					+ "');");
			while (res.next()) {
				// List<String> dataList = new ArrayList<String>();
				String date = "";
				String tempType = res.getString(3);
				if (null != tempType && !tempType.equals("")) {
					date = tempType.substring(2);
				}
				// dataList.add(date);// 日期，图横坐标
				// dataList.add(res.getString(2));// 企业名称
				// dataList.add(tempType);// 类型
				// dataList.add(res.getString(4));// 持仓量
				// dataList.add(res.getString(5));// 持仓均价
				// dataList.add(res.getString(6));// 现价
				// dataList.add(res.getString(7));// 盈亏比例
				// dataList.add(res.getString(8));// 盈亏金额
				// QHMXList.add(dataList);
				QHMXBean qhmxbean = new QHMXBean();
				qhmxbean.setQybh(res.getString(1));
				qhmxbean.setQymc(res.getString(2));
				qhmxbean.setType(res.getString(3));
				qhmxbean.setCcl(res.getString(4));
				qhmxbean.setCcjj(res.getString(5));
				qhmxbean.setPrice(res.getString(6));
				qhmxbean.setYk(res.getString(7));
				qhmxbean.setYkje(res.getString(8));

				qhmxbean.setDate(date);
				QHMXList.add(qhmxbean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return QHMXList;
	}

}
