package com.tbea.test.testWebProject.model.dao.ydzb;

import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;
import com.tbea.test.testWebProject.model.entity.local.YQK;
import com.tbea.test.testWebProject.service.ydzb.Company;



public interface YDZBDao{

	public List<YDZBBean> getYDZB(Calendar cal, Company company);
	public List<YDZBBean> getYDZB_V2(Calendar cal, Company company);
	public List<XJL> getXJL(Calendar cal);
	public void uninit();
	public void init();
}
