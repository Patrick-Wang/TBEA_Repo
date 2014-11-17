package com.tbea.test.testWebProject.service.yszkjgqk;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.yszkjgqk.YSZKJGQKDao;
import com.tbea.test.testWebProject.model.entity.YSZKJGQK;

@Service
@Transactional("transactionManager2")
public class YSZKJGQKServiceImpl implements YSZKJGQKService {

	@Autowired
	private YSZKJGQKDao yszkjgqkDao;

	
	private static Map<String, Integer> hyMap = new HashMap<String, Integer>();
	static {
		hyMap.put("国网", 0);
		hyMap.put("南网", 1);
		hyMap.put("省、市电力系统", 2);
		hyMap.put("五大发电", 3);
		hyMap.put("其他电源", 4);
		hyMap.put("出口合同", 5);
		hyMap.put("其他", 6);
		hyMap.put("合计", 7);
	}
	
	//return value format according to yszkjgqk
	//hy one
	//	[... current year's je, zqbbl ...]
	//hy two
	//	[... current year's je, zqbbl ...]
	//......
	@Override
	public String[][] getYszkjg(Date d, Company comp) {
		String[][] result = new String[hyMap.size()][10];
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		List<YSZKJGQK> list = yszkjgqkDao.getYszkjg(cal, comp);
		for (YSZKJGQK ysk : list){
			result[hyMap.get(ysk.getHy())][0] = ysk.getYsje() + "";
			result[hyMap.get(ysk.getHy())][1] = ysk.getZqbbl() + "";
			result[hyMap.get(ysk.getHy())][2] = ysk.getYyn()+ "";
			result[hyMap.get(ysk.getHy())][3] = ysk.getYdsy() + "";
			result[hyMap.get(ysk.getHy())][4] = ysk.getSdly() + "";
			result[hyMap.get(ysk.getHy())][5] = ysk.getLdsey() + "";
			result[hyMap.get(ysk.getHy())][6] = ysk.getYnys() + "";
			result[hyMap.get(ysk.getHy())][7] = ysk.getWdqk() + "";
			result[hyMap.get(ysk.getHy())][8] = ysk.getWdqzbj() + "";
			result[hyMap.get(ysk.getHy())][9] = ysk.getYszkhj() + "";
		}
		return result;
	}

	//return value format
	//hy one
	//	[... previous year's total yqk from January to current month...]
	//	[... previous year's wdqk from January to current month...]
	//	[... previous year's yszkhj from January to current month...]
	//	[... current year's total yqk from January to current month...]
	//	[... current year's wdqk from January to current month...]
	//	[... current year's yszkhj from January to current month...]
	//hy two
	//	[... previous year's total yqk from January to current month...]
	//	[... previous year's wdqk from January to current month...]
	//	[... previous year's yszkhj from January to current month...]
	//	[... current year's total yqk from January to current month...]
	//	[... current year's wdqk from January to current month...]
	//	[... current year's yszkhj from January to current month...]
	//......
	@Override
	public String[][] getWdqtbbh(Date d, Company comp) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		String[][] result = new String[6*hyMap.size()][cal.get(Calendar.MONTH) + 1];
		
		List<YSZKJGQK> list = yszkjgqkDao.getWdqtbbh(cal, comp);
		int yearBase = 0;
		Calendar time =  Calendar.getInstance();
		int month = 0;
		for (YSZKJGQK ysk : list){
			time.setTime(Util.valueOf(ysk.getNy()));
			month = time.get(Calendar.MONTH);
			if (time.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
				//previous year
				yearBase = 0;
			}
			else{
				//current year
				yearBase = 1;
			}
			
			result[yearBase * 3 + hyMap.get(ysk.getHy()) * 6][month] = 
					ysk.getYyn() + 
					ysk.getYdsy() + 
					ysk.getSdly() + 
					ysk.getLdsey() + 
					ysk.getYnys() + "";
			result[yearBase * 3 + hyMap.get(ysk.getHy()) * 6 + 1][month] = ysk.getWdqk() + "";
			result[yearBase * 3 + hyMap.get(ysk.getHy()) * 6 + 2][month] = ysk.getYszkhj() + "";
		}
		
		return result;
	}

	//return value format
	//hy one
	//	[...previous year data from January to current month...]
	//	[... current year data from January to current month...]
	//hy two
	//	[...previous year data...]
	//	[... current year data...]
	//......
	@Override
	public String[][] getJetbbh(Date d, Company comp) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		String[][] result = new String[2*hyMap.size()][cal.get(Calendar.MONTH) + 1];
		
		List<YSZKJGQK> list = yszkjgqkDao.getJetbbh(cal, comp);
		int yearBase = 0;
		Calendar time =  Calendar.getInstance();
		for (YSZKJGQK ysk : list){
			time.setTime(Util.valueOf(ysk.getNy()));
			if (time.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
				//previous year
				yearBase = 0;
			}
			else{
				//current year
				yearBase = 1;
			}
			
			result[yearBase + hyMap.get(ysk.getHy()) * 2][time.get(Calendar.MONTH)] = 
					ysk.getYsje() + "";
		}
		
		return result;
	}



}
