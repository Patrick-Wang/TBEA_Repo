package com.tbea.ic.operation.model.dao.jygk.sjzb;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.SJZB;

public interface SJZBDao  extends AbstractReadWriteDao<SJZB>{
	SJZB getZb(Integer zb, Date date, Company company);

	List<SJZB> getZbs(Date date, Company company);

	List<SJZB> getZbs(Date date, List<Company> comps);

	List<SJZB> getUnapprovedZbs(Date date, List<Company> comps);
	
	List<SJZB> getApprovedZbs(Date date, List<Company> comps);
	
	Double[] GetMonthActualValue(List<Company> comps, Date date, List<Integer> indexlist);
	Double[] GetSeasonSumValue(List<Company> comps, Date date, List<Integer> indexlist);
	Double[] GetSeasonSumActualValue(List<Company> comps, Date date, List<Integer> indexlist);
	//List<Double[]> GetSBDSpecialIndex(Date d)

	List<Integer> getCompanies();
}
