package com.tbea.ic.operation.model.dao.jygk;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJSJZB;

public interface YJSJZBDao{
	YJSJZB getZb(Integer id, Date date, Company company);
	List<YJSJZB> getZbs(Date date, Company company);
	YJSJZB create();
	void update(YJSJZB zb);
	void insert(YJSJZB zb);
	YJSJZB find(Integer id);
}
