package com.tbea.ic.operation.service.yqkbhqs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.cqk.CQKDao;
import com.tbea.ic.operation.model.dao.yqkbhqs.YQKBHQSDao;
import com.tbea.ic.operation.model.entity.YQKBHQS;

@Service
@Transactional("transactionManager")
public class YQKBHQSServiceImpl implements YQKBHQSService {

	@Autowired
	private YQKBHQSDao yqkbhqsDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public void setYqkbhqs(String[][] result, Calendar cal, List<YQKBHQS> list){
		int[] total = new int[]{0, 0, 0, 0, 0};
		Calendar time = Calendar.getInstance();
		int month = 0;
		for (YQKBHQS yqk : list){
			time.setTime(Util.valueOf(yqk.getNy()));
			month = time.get(Calendar.MONTH);
			result[month][0] = Util.toDouble(result[month][0]) + yqk.getYyn() + "";
			result[month][1] = Util.toDouble(result[month][1]) + yqk.getYdsy() + "";
			result[month][2] = Util.toDouble(result[month][2]) + yqk.getSdly() + "";
			result[month][3] = Util.toDouble(result[month][3]) + yqk.getLdsey() + "";
			result[month][4] = Util.toDouble(result[month][4]) + yqk.getYnys() + "";
			total[0] += yqk.getYyn();
			total[1] += yqk.getYdsy();
			total[2] += yqk.getSdly();
			total[3] += yqk.getLdsey();
			total[4] += yqk.getYnys();
		}
		
		for (int i = 0; i < 5; ++i){
			result[12][i] = Util.toDouble(result[12][i]) + total[i] + "";
		}
	}
	
	//return value format according to yqkqsbhb
	//January 
	//	[... current year data...]
	//February 
	//	[... current year data...]
	//......
	//Total
	//	[... current year data...]
	@Override
	public String[][] getYqkbhqsData(Date d, Company comp) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		String[][] result = new String[13][5];
		setYqkbhqs(result, cal, yqkbhqsDao.getYqkbhqsOfThisYear(cal, comp));
		return result;
	}

	@Override
	public Date getLatestDate() {
		YQKBHQS yqkbhqs = yqkbhqsDao.getLatestDate();
		if (null != yqkbhqs){
			return (Date) Util.valueOf(yqkbhqs.getNy());
		}
		return null;
	}

	@Override
	public String[][] getYqkbhqsData(Date d, List<Company> subCompanys) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
		String[][] result = new String[13][5];
		setYqkbhqs(result, cal, yqkbhqsDao.getYqkbhqsOfThisYear(cal, subCompanys));
		return result;
	}


}
