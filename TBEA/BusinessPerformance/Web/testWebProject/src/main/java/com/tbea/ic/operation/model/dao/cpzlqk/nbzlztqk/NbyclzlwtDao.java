package com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.NbyclzlwtEntity;



public interface NbyclzlwtDao extends AbstractReadWriteDao<NbyclzlwtEntity> {

	Integer getCount(Date d, Company company);

	Integer getCount(Date date, Date d, Company company);

	Integer getCount(Date d, List<Company> comps);

	Integer getCount(Date date, Date d, List<Company> comps);

	Integer getSjzlqkCount(Date d, Company company);

	Integer getSjzlqkCount(Date date, Date d, List<Company> comps);

	Integer getSjzlqkCount(Date d, List<Company> comps);

	Integer getSjzlqkCount(Date date, Date d, Company company);

	Integer getGyzlwtCount(Date d, Company company);

	Integer getGyzlwtCount(Date date, Date d, Company company);

	Integer getGyzlwtCount(Date d, List<Company> comps);

	Integer getGyzlwtCount(Date date, Date d, List<Company> comps);

	Integer getYclzlRcjcwtCount(Date date, Date d, Company company);
	Integer getYclzlRcjcwtCount(Date date, Date d, List<Company> comps);

	Integer getYclzlCnzzwtCount(Date date, Date d,
			Company company);
	Integer getYclzlCnzzwtCount(Date date, Date d,
			List<Company> comps);

	Integer getYclzlRcjcwtCount(Date date, Company company);
	Integer getYclzlCnzzwtCount(Date date, Company company);

	Integer getYclzlRcjcwtCount(Date d, List<Company> comps);

	Integer getYclzlCnzzwtCount(Date d, List<Company> comps);

	Integer getSczzzlqkCount(Date date, Date date2, Company company);

	Integer getSczzzlqkCount(Date date, Date date2, List<Company> comps);

	Integer getSczzzlqkCount(Date date, List<Company> subCompanies);

	Integer getSczzzlqkCount(Date d, Company company);

	List<String> getXmgs(Company company);

	Integer getSczzzlqkxxxxCount(Date d, Company comp, String gs);

	Integer getSczzzlqkxxxxCount(Date date, Date d, Company comp, String gs);


}
