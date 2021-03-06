package com.tbea.ic.operation.model.dao.yszkjgqk;

import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YSZKJGQK;

public interface YSZKJGQKDao extends AbstractReadWriteDao<YSZKJGQK> {

	List<YSZKJGQK> getYszkjg(Calendar cal, Company comp);

	List<YSZKJGQK> getJetbbh(Calendar cal, Company comp);

	List<YSZKJGQK> getWdqtbbh(Calendar cal, Company comp);

	boolean hasCompany(Company comp);

	YSZKJGQK getLatestYszkjg();

	List<YSZKJGQK> getYszkjg(Calendar cal, List<Company> comps);

	List<YSZKJGQK> getWdqtbbh(Calendar cal, List<Company> comps);

	List<YSZKJGQK> getJetbbh(Calendar cal, List<Company> comps);

}
