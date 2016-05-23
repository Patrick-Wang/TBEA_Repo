package com.tbea.ic.operation.model.dao.ydzb;

import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.XJL;
import com.tbea.ic.operation.model.entity.YDZBBean;
import com.tbea.ic.operation.model.entity.local.XJLRB;
import com.tbea.ic.operation.model.entity.local.YDZBFDW;
import com.tbea.ic.operation.model.entity.local.ZBHZ;



public interface YDZBDao{

	public List<YDZBBean> getYDZB(Calendar cal, Company company);
	public List<YDZBBean> getYDZB_V2(Calendar cal, Company company);
	public List<XJL> getXJL(Calendar cal);
	public ZBHZ getLatestZbhj();
	public YDZBFDW getLatestYdzbfdw();
	public XJLRB getLatestXjlrb();
}
