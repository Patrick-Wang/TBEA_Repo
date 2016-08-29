package com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.WbyclzlwtEntity;



public interface WbyclzlwtDao extends AbstractReadWriteDao<WbyclzlwtEntity> {

	Integer getCount(Date d, Company company);

	Integer getCount(Date date, Date d, Company company);

	Integer getCount(Date d, List<Company> comps);

	Integer getCount(Date date, Date d, List<Company> comps);

	Integer getSjzlqkCount(Date d, List<Company> comps);

	Integer getSjzlqkCount(Date date, Date d, List<Company> comps);

	Integer getSjzlqkCount(Date d, Company company);

	Integer getSjzlqkCount(Date date, Date d, Company company);

	Integer getGyzlwtCount(Date date, Date d, Company company);

	Integer getGyzlwtCount(Date d, List<Company> comps);

	Integer getGyzlwtCount(Date d, Company company);

	Integer getGyzlwtCount(Date date, Date d, List<Company> comps);

	Integer getYclzlCwxcwtCount(Date date, Date d,
			Company company);

	Integer getYclzlCwxcwtCount(Date date, Date d,
			List<Company> comps);

	Integer getYclzlCwxcwtCount(Date date, Company company);

	Integer getYclzlCwxcwtCount(Date d, List<Company> comps);

	Integer getSczzzlqkCount(Date date, Date date2, Company company);

	Integer getSczzzlqkCount(Date date, Date date2, List<Company> comps);

	Integer getSczzzlqkCount(Date date, List<Company> subCompanies);

	Integer getSczzzlqkCount(Date d, Company company);

	List<String> getXmgs(Company company);

	Integer getSczzzlqkxxxxCount(Date d, Company company, String gs);

	Integer getSczzzlqkxxxxCount(Date date, Date d, Company company, String gs);

	Integer getYsazzlwtCount(Date date, Date d, Company company);

	Integer getYsazzlwtCount(Date date, Date d, List<Company> comps);

	Integer getYsazzlwtCount(Date d, Company company);

	Integer getYsazzlwtCount(Date d, List<Company> comps);

	List<String> getSubIssues(List<Company> comps);

	Integer getWbzlwtflCount(Date date, Date date2, Company comp, String issue);

	Integer getWbzlwtflCount(Date date, Company comp, String issue);

	List<String> getSubIssues(Company comp);

	List<WbyclzlwtEntity> getAll(Date d, Company company);
}
