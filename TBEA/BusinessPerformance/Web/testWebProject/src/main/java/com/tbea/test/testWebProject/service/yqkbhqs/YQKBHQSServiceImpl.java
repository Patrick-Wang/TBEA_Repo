package com.tbea.test.testWebProject.service.yqkbhqs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.cqk.CQKDao;
import com.tbea.test.testWebProject.model.dao.yqkbhqs.YQKBHQSDao;
import com.tbea.test.testWebProject.model.entity.YQKBHQS;

@Service
@Transactional("transactionManager2")
public class YQKBHQSServiceImpl implements YQKBHQSService {

	@Autowired
	private YQKBHQSDao yqkbhqsDao;

	//return value format according to yqkqsbhb
	//January 
	//	[... current year data...]
	//February 
	//	[... current year data...]
	//......
	//Total
	//	[... current year data...]
	@Override
	public String[][] getYqkbhqsData(Date d) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		String[][] result = new String[13][5]; 
		List<YQKBHQS> list = yqkbhqsDao.getYqkbhqsOfThisYear(cal);
		
		int[] total = new int[]{0, 0, 0, 0, 0};
		Calendar time = Calendar.getInstance();
		int month = 0;
		for (YQKBHQS yqk : list){
			time.setTime(Util.valueOf(yqk.getNy()));
			month = time.get(Calendar.MONTH);
			result[month][0] = yqk.getYyn() + "";
			result[month][1] = yqk.getYdsy() + "";
			result[month][2] = yqk.getSdly() + "";
			result[month][3] = yqk.getLdsey() + "";
			result[month][4] = yqk.getYnys() + "";
			total[0] += yqk.getYyn();
			total[1] += yqk.getYdsy();
			total[2] += yqk.getSdly();
			total[3] += yqk.getLdsey();
			total[4] += yqk.getYnys();
		}
		
		for (int i = 0; i < 5; ++i){
			result[12][i] = total[i] + "";
		}
		
		return result;
	}


}
