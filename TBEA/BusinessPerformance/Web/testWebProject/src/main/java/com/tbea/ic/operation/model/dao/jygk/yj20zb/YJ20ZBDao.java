package com.tbea.ic.operation.model.dao.jygk.yj20zb;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;

public interface YJ20ZBDao  extends AbstractReadWriteDao<YJ20ZB> {

	YJ20ZB getZb(Integer zbId, Date date, Company company);

	List<YJ20ZB> getZbs(Date date, Company company);

	List<YJ20ZB> getApprovedZbs(Date date, Company company);

	List<YJ20ZB> getUnapprovedZbs(Date date, List<Company> comps);

	List<YJ20ZB> getZb(List<Company> comps, Date date, Date dEnd);

	List<YJ20ZB> getZb(Date date, List<Company> comps);

	List<YJ20ZB> getApprovedZbs(Date date, List<Company> comps);

	List<Integer> getCompanies();

}