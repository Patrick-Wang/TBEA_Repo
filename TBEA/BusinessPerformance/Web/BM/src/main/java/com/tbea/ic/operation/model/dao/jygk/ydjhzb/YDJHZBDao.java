package com.tbea.ic.operation.model.dao.jygk.ydjhzb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

public interface YDJHZBDao  extends AbstractReadWriteDao<YDJHZB>{

	YDJHZB getZb(Integer id, Date date, Company company);

	List<YDJHZB> getZbs(Date date, Company company);

	List<YDJHZB> getZb(List<Company> comps, Date dStart, Date dEnd);
	
	List<YDJHZB> getZb(Company comp, Date dStart, Date dEnd);

//	List<YDJHZB> getUnapprovedZbs(Date date, Company company);

//	List<YDJHZB> getApprovedZbs(Date date, Company company);

	List<Integer> getCompanies();

	List<Double> getDyjhz(Date start, Date end, List<Integer> zbsTmp,
			List<Company> companies);

	//int getApprovedZbsCount(Date date, Company company);

	List<Object[]> getEntryCompletedCompanies(Date date);

	Timestamp getEntryTime(Date date, Company comp);

	//int getSavedZbsCount(Date date, Company company);

	ZBStatus getZbStatus(Date date, Company company);

	List<Integer> getApprovedCompletedCompanies(Date date);

	Timestamp getApprovedTime(Date date, Company comp);
}
