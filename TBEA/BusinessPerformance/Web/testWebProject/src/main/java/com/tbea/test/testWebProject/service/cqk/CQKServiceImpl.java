package com.tbea.test.testWebProject.service.cqk;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.cqk.CQKDao;
import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.entity.local.CQK;

@Transactional("transactionManager")
public class CQKServiceImpl implements CQKService {

	private CQKDao cqkDao;

	private YSZKTZLocalDao yszktzLocalDao;
private static Map<String, Integer> hyMap = new HashMap<String, Integer>();
	static {
		hyMap.put("国网、南网", 0);
		hyMap.put("省、市电力系统", 1);
		hyMap.put("五大发电", 2);
		hyMap.put("其他电源", 3);
		hyMap.put("石油石化", 4);
		hyMap.put("轨道交通", 5);
		hyMap.put("出口合同", 6);
		hyMap.put("其他", 7);
		hyMap.put("合计", 8);
	}
	private void importCQKByHY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		CQK cqk = new CQK();
		cqk.setNy(baseMonth);
		cqk.setHy(hyName);
		cqk.setCqk2n(yszktzLocalDao.getCQK(baseMonth, null, 730, sshyList,
				isIncluded, isTotal));
		cqk.setCqk3n(yszktzLocalDao.getCQK(baseMonth, 730, 1095, sshyList,
				isIncluded, isTotal));
		cqk.setCqk4njzq(yszktzLocalDao.getCQK(baseMonth, 1095, null, sshyList,
				isIncluded, isTotal));
		cqk.setZj(yszktzLocalDao.getCQK(baseMonth, null, null, sshyList,
				isIncluded, isTotal));
		cqkDao.merge(cqk);
		return;
	}

	@Override
	public boolean importCQK() {
		boolean result = false;
		try {
			List<String> monthList = new ArrayList<String>();
			monthList.add("201301");
			monthList.add("201302");
			monthList.add("201303");
			monthList.add("201304");
			monthList.add("201305");
			monthList.add("201306");
			monthList.add("201307");
			monthList.add("201308");
			monthList.add("201309");
			monthList.add("201310");
			monthList.add("201311");
			monthList.add("201312");
			monthList.add("201401");
			monthList.add("201402");
			monthList.add("201403");
			monthList.add("201404");
			monthList.add("201405");
			monthList.add("201406");
			monthList.add("201407");
			monthList.add("201408");
			monthList.add("201409");
			monthList.add("201410");
			monthList.add("201411");
			monthList.add("201412");
			for (String baseMonth : monthList) {
				List<String> sshyGWNWList = new ArrayList<String>();
				sshyGWNWList.add("01");
				sshyGWNWList.add("02");
				importCQKByHY(baseMonth, "国网、南网", sshyGWNWList, true, false);

				List<String> sshySSDLList = new ArrayList<String>();
				sshySSDLList.add("08");
				importCQKByHY(baseMonth, "省、市电力系统", sshySSDLList, true, false);

				List<String> sshyWDFDList = new ArrayList<String>();
				sshyWDFDList.add("03");
				sshyWDFDList.add("04");
				sshyWDFDList.add("05");
				sshyWDFDList.add("06");
				sshyWDFDList.add("07");
				importCQKByHY(baseMonth, "五大发电", sshyWDFDList, true, false);

				List<String> sshyQTDYList = new ArrayList<String>();
				sshyQTDYList.add("09");
				importCQKByHY(baseMonth, "其他电源", sshyQTDYList, true, false);

				List<String> sshySYSHList = new ArrayList<String>();
				sshySYSHList.add("16");
				importCQKByHY(baseMonth, "石油石化", sshySYSHList, true, false);

				List<String> sshyGDJTList = new ArrayList<String>();
				sshyGDJTList.add("14");
				sshyGDJTList.add("15");
				importCQKByHY(baseMonth, "轨道交通", sshyGDJTList, true, false);

				List<String> sshyCKHTList = new ArrayList<String>();
				sshyCKHTList.add("14");
				sshyCKHTList.add("15");
				importCQKByHY(baseMonth, "出口合同", sshyCKHTList, true, false);

				List<String> sshyQTList = new ArrayList<String>();
				sshyQTList.addAll(sshyGWNWList);
				sshyQTList.addAll(sshySSDLList);
				sshyQTList.addAll(sshyWDFDList);
				sshyQTList.addAll(sshyQTDYList);
				sshyQTList.addAll(sshySYSHList);
				sshyQTList.addAll(sshyGDJTList);
				sshyQTList.addAll(sshyCKHTList);
				importCQKByHY(baseMonth, "其他", sshyQTList, false, false);

				importCQKByHY(baseMonth, "合计", null, false, true);
			}
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public CQKDao getCqkDao() {
		return cqkDao;
	}

	public void setCqkDao(CQKDao cqkDao) {
		this.cqkDao = cqkDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

	//return value format according to cqk
	//hy one
	//	[... current year data...]
	//hy two 
	//	[... current year data...]
	@Override
	public String[][] getCqkData(Date d) {
		List<CQK> list = cqkDao.getCqkData(d);
		
		String[][] result = new String[hyMap.size()][4]; 
		for (CQK cqk : list){
			if (cqk != null && hyMap.get(cqk.getHy()) != null){
				result[hyMap.get(cqk.getHy()).intValue()][0] = cqk.getCqk4njzq() + "";
				result[hyMap.get(cqk.getHy()).intValue()][1] = cqk.getCqk3n() + "";
				result[hyMap.get(cqk.getHy()).intValue()][2] = cqk.getCqk2n() + "";
				result[hyMap.get(cqk.getHy()).intValue()][3] = cqk.getZj() + "";
			}
		}
		return result;
	}

	//return value format
	//hy one 
	//	[... previous year's cqk zj(refer to CQK entity) from January to current month...]
	//	[... current year's cqk zj(refer to CQK entity) from January to current month...]
	//	[... current year's cqk ln(refer to CQK entity) from January to current month...]
	//	[... current year's cqk Sn(refer to CQK entity) from January to current month...]
	//	[... current year's cqk Snjzq(refer to CQK entity) from January to current month...]
	//hy two 
	//	[... previous year's cqk zj(refer to CQK entity) from January to current month...]
	//	[... current year's cqk zj(refer to CQK entity) from January to current month...]
	//	[... current year's cqk ln(refer to CQK entity) from January to current month...]
	//	[... current year's cqk Sn(refer to CQK entity) from January to current month...]
	//	[... current year's cqk Snjzq(refer to CQK entity) from January to current month...]
	//......
	@Override
	public String[][] getCompareData(Date d) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
      
		String[][] result = new String[5 * hyMap.size()][cal.get(Calendar.MONTH) + 1]; 
		
		List<CQK> list = cqkDao.getPreYearCQK(d);
		Calendar time = Calendar.getInstance();
		int month = 0;
		for (CQK cqk : list){
			if (cqk != null && hyMap.get(cqk.getHy()) != null){
				time.setTime(Util.valueOf(cqk.getNy()));
				month = time.get(Calendar.MONTH);
				result[0 + hyMap.get(cqk.getHy()).intValue() * 5][month] = cqk.getZj() + "";
			}
		}
		
		list = cqkDao.getCurYearCQK(d);
		for (CQK cqk : list){
			if (cqk != null && hyMap.get(cqk.getHy()) != null){
				time.setTime(Util.valueOf(cqk.getNy()));
				month = time.get(Calendar.MONTH);
				result[1 + hyMap.get(cqk.getHy()).intValue() * 5][month] = cqk.getZj() + "";
				result[2 + hyMap.get(cqk.getHy()).intValue() * 5][month] = cqk.getCqk2n() + "";
				result[3 + hyMap.get(cqk.getHy()).intValue() * 5][month] = cqk.getCqk3n() + "";
				result[4 + hyMap.get(cqk.getHy()).intValue() * 5][month] = cqk.getCqk4njzq() + "";
			}
		}
		return result;
	}
}
