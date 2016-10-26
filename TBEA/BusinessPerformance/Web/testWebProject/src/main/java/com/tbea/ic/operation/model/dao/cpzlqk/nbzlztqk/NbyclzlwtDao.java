package com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.NbyclzlwtEntity;



public interface NbyclzlwtDao extends AbstractReadWriteDao<NbyclzlwtEntity> {

	Integer getCount(Date d, Company company, List<Integer> zts);

	Integer getCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date d, Company company, List<Integer> zts);

	Integer getSjzlqkCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getSjzlqkCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date date, Date d, Company company, List<Integer> zts);

	Integer getGyzlwtCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getGyzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getYclzlRcjcwtCount(Date date, Date d, Company company, List<Integer> zts);
	Integer getYclzlRcjcwtCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getYclzlCnzzwtCount(Date date, Date d,
			Company company, List<Integer> zts);
	Integer getYclzlCnzzwtCount(Date date, Date d,
			List<Company> comps, List<Integer> zts);

	Integer getYclzlRcjcwtCount(Date date, Company company, List<Integer> zts);
	Integer getYclzlCnzzwtCount(Date date, Company company, List<Integer> zts);

	Integer getYclzlRcjcwtCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getYclzlCnzzwtCount(Date d, List<Company> comps, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, Date date2, Company company, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, Date date2, List<Company> comps, List<Integer> zts);

	Integer getSczzzlqkCount(Date date, List<Company> subCompanies, List<Integer> zts);

	Integer getSczzzlqkCount(Date d, Company company, List<Integer> zts);

	List<String> getXmgs(Company company, List<Integer> zts);

	Integer getSczzzlqkxxxxCount(Date d, Company comp, String gs, List<Integer> zts);

	Integer getSczzzlqkxxxxCount(Date date, Date d, Company comp, String gs, List<Integer> zts);

	Integer getYsazzlwtCount(Date date, Date d, Company comp, List<Integer> zts);

	Integer getYsazzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts);

	Integer getYsazzlwtCount(Date d, Company company, List<Integer> zts);

	Integer getYsazzlwtCount(Date d, List<Company> comps, List<Integer> zts);

//	List<String> getSubIssues(List<Company> comps, List<Integer> zts);
//
//	List<String> getSubIssues(Company company, List<Integer> zts);

	Integer getNbzlwtflCount(Date date, Date d,
			Company comp, String issue, List<Integer> zts);

	Integer getNbzlwtflCount(Date date, Company comp, String issue, List<Integer> zts);

	List<NbyclzlwtEntity> getAll(Date d, Company company, List<Integer> zts);

	List<NbyclzlwtEntity> getAllIgnoreStatus(Date d, Company company);

	List<String> getIssues(List<Company> comps, List<Integer> zts);
	
	List<String> getIssues(Company company, List<Integer> zts);


}
