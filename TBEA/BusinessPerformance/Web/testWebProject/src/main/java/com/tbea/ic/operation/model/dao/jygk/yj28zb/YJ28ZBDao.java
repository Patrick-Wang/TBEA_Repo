package com.tbea.ic.operation.model.dao.jygk.yj28zb;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

public interface YJ28ZBDao extends AbstractReadWriteDao<YJ28ZB>{

	YJ28ZB getZb(Integer zb, Date date, Company company);

	List<YJ28ZB> getZbs(Date date, Company company);

	List<YJ28ZB> getApprovedZbs(Date date, Company company);

	List<YJ28ZB> getUnapprovedZbs(Date date, List<Company> comps);

	List<YJ28ZB> getZb(List<Company> comps, Date dStart, Date dEnd);

	List<YJ28ZB> getZb(Date date, List<Company> comps);

	List<YJ28ZB> getApprovedZbs(Date date, List<Company> comps);

	List<Integer> getCompanies();


}
