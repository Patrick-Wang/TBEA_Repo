package com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.WbyclzlwtEntity;



public interface WbyclzlwtDao extends AbstractReadWriteDao<WbyclzlwtEntity> {

	Integer getCount(Date d, Company company, List<Integer> zts);

	Integer getCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date d, Company company, List<Integer> zts);

	Integer getSjzlqkCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getGyzlwtCount(Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getYclzlCwxcwtCount(Date date, Date d,
			Company company, List<Integer> zts);

	Integer getYclzlCwxcwtCount(Date date, Date d,
			List<Company> comps, List<Integer> zts);

	Integer getYclzlCwxcwtCount(Date date, Company company, List<Integer> zts);

	Integer getYclzlCwxcwtCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, Date date2, Company company, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, Date date2, List<Company> comps, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, List<Company> subCompanies, List<Integer> zts);

	Integer getSczzzlqkCount(Date d, Company company, List<Integer> zts);

	List<String> getXmgs(Company company, List<Integer> zts);

	Integer getSczzzlqkxxxxCount(Date d, Company company, String gs, List<Integer> zts);

	Integer getSczzzlqkxxxxCount(Date date, Date d, Company company, String gs, List<Integer> zts);

	Integer getYsazzlwtCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getYsazzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getYsazzlwtCount(Date d, Company company, List<Integer> zts);

	Integer getYsazzlwtCount(Date d, List<Company> comps, List<Integer> zts);

	//List<String> getSubIssues(List<Company> comps, List<Integer> zts);

	Integer getWbzlwtflCount(Date date, Date date2, Company comp, String issue, List<Integer> zts);

	Integer getWbzlwtflCount(Date date, Company comp, String issue, List<Integer> zts);

	//List<String> getSubIssues(Company comp, List<Integer> zts);

	List<WbyclzlwtEntity> getAll(Date d, Company company, List<Integer> zts);

	List<WbyclzlwtEntity> getAllIgnoreStatus(
			Date d, Company company);

	List<String> getIssues(List<Company> comps, List<Integer> zts);
	
	List<String> getIssues(Company comp, List<Integer> zts);
}
