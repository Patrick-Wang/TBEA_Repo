package com.tbea.ic.operation.model.dao.jygk.sjzb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;

public interface SJZBDao  extends AbstractReadWriteDao<SJZB>{
	SJZB getZb(Integer zb, Date date, Company company);

	List<SJZB> getZbs(Date date, Company company);

	List<SJZB> getZbs(Date date, List<Company> comps);

//	List<SJZB> getUnapprovedZbs(Date date, List<Company> comps);
	
//	List<SJZB> getApprovedZbs(Date date, List<Company> comps);
	
	Double[] GetMonthActualValue(List<Company> comps, Date date, List<Integer> indexlist);
	Double[] GetSeasonSumValue(List<Company> comps, Date date, List<Integer> indexlist);
	Double[] GetSeasonSumActualValue(List<Company> comps, Date date, List<Integer> indexlist);
	//List<Object[]> GetYearSumValue(List<Company> comps, Date date, List<Integer> indexlist);
	List<Object[]> GetMonthPlanValue(List<Company> comps, Date date, List<Integer> indexlist);
	//List<Double[]> GetSBDSpecialIndex(Date d)

	List<Integer> getCompanies();

	List<SJZB> getSjzbs(List<YDZBZT> sjzbzts, List<Integer> zbs);

//	int getApprovedZbsCount(Date date, Company company);

	List<Object[]> getEntryCompletedCompanies(Date date);

	Timestamp getEntryTime(Date date, Company comp);

//	int getSavedZbsCount(Date date, Company company);
	
	ZBStatus getZbStatus(Date date, Company company);

	List<Integer> getApprovedCompletedCompanies(Date date);

	Timestamp getApprovedTime(Date date, Company comp);

	Double getZb(Integer indi, Date d, List<Integer> ids);
}
