package com.tbea.ic.operation.model.dao.nc;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NCZB;

public interface NCZBDao extends AbstractReadWriteDao<NCZB> {

	public NCZB getNCZB(Company company, int zbid, int nf, int yf);

	public List<NCZB> getNCZBByDate(int nf, int yf);

	public List<Double> getSjzbs(Date start, Date end, List<Integer> zbs,
			List<Company> companies);

}
