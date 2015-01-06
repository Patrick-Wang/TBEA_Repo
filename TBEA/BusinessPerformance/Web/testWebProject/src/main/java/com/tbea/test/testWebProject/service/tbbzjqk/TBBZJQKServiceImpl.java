package com.tbea.test.testWebProject.service.tbbzjqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.dao.tbbzjqk.TBBZJQKDao;
import com.tbea.test.testWebProject.model.entity.TBBZJXX;
import com.tbea.test.testWebProject.model.entity.local.CQK;
@Service
@Transactional("transactionManager")
public class TBBZJQKServiceImpl implements TBBZJQKService{

	@Autowired
	TBBZJQKDao tbjDao;
	
	public String[] getTbbzjqkData(Date d, Company comp){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String[] ret = new String[c.get(Calendar.MONTH) + 1];
		List<TBBZJXX> tbjs = tbjDao.getTbbzj(d, comp);
		for (TBBZJXX tbj : tbjs){
			ret[tbj.getYf() - 1] = tbj.getJe() + "";
		}
		return ret;
	}

	@Override
	public Date getLatestDate() {
		TBBZJXX tb = tbjDao.getLatestTBJ();
		if (null != tb){
			return (Date)Date.valueOf(tb.getNf() + "-" + tb.getYf() + "-1");
		}
		return null;
	}
	
}
