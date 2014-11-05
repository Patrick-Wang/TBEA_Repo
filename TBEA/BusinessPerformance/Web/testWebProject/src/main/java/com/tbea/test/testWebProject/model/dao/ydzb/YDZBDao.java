package com.tbea.test.testWebProject.model.dao.ydzb;

import java.util.Calendar;
import java.util.List;

import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;



public interface YDZBDao {

	public List<YDZBBean> getYDZB(Calendar cal, int[] companys);
	public List<YDZBBean> getYDZB_V2(Calendar cal, int company);
	public List<YDZBBean> getYDZB_V2(Calendar cal, int[] companys);
	public List<YDZBBean> getYDZB(Calendar cal, int company);
	public List<XJL> getXJL(Calendar cal);

}
