package com.tbea.ic.operation.model.dao.jygk.qnjh;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;

public interface NDJHZBDao  extends AbstractReadWriteDao<NDJHZB>{

	NDJHZB getZb(Integer zb, Date date, Company company);

	List<NDJHZB> getZbs(Date date, Company company);

	List<NDJHZB> getZbs(Date date, List<Company> comps);

	List<NDJHZB> getUnapprovedZbs(Date date, List<Company> comps);

	List<NDJHZB> getApprovedZbs(Date date, List<Company> comps);

	List<Integer> getCompanies();

	List<Double> getQnjhz(Date date, List<Integer> zbIds, List<Company> companies);

}
