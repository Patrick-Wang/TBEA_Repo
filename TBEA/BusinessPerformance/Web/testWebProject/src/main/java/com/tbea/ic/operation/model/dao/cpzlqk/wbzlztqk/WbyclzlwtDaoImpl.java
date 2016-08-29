package com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.WbyclzlwtEntity;



@Repository(WbyclzlwtDaoImpl.NAME)
@Transactional("transactionManager")
public class WbyclzlwtDaoImpl extends AbstractReadWriteDaoImpl<WbyclzlwtEntity> implements WbyclzlwtDao {
	public final static String NAME = "WbyclzlwtDaoImpl";

	@Override
	public Integer getCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCwxcwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCwxcwtCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCwxcwtCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCwxcwtCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}


	@Override
	public List<String> getXmgs(Company company) {
		Query q = getEntityManager().createQuery(
				"select responsibility_department from WbyclzlwtEntity where company_name = :comp " +
				"group by responsibility_department");
		q.setParameter("comp", company.getName());
		return q.getResultList();
	}

	@Override
	public Integer getSczzzlqkxxxxCount(Date d, Company comp, String gs) {
		Query q = getEntityManager().createQuery(
				"select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"responsibility_department = :gs ");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", d);
		q.setParameter("gs", gs);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkxxxxCount(Date date, Date d, Company comp, String gs) {
		Query q = getEntityManager().createQuery(
				"select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"responsibility_department = :gs ");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("gs", gs);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date date, Date d, Company comp) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type in ('安装问题','运输问题')");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type in ('安装问题','运输问题')");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type in ('安装问题','运输问题')");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type in ('安装问题','运输问题')");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public List<String> getSubIssues(List<Company> comps) {
		Query q = getEntityManager().createQuery(
				"select sub_issue_type from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") " +
				"group by sub_issue_type");
		return q.getResultList();
	}
	
	@Override
	public List<String> getSubIssues(Company company) {
		Query q = getEntityManager().createQuery(
				"select sub_issue_type from NbyclzlwtEntity where company_name = :comp " +
				"group by sub_issue_type");
		q.setParameter("comp", company.getName());
		return q.getResultList();
	}

	@Override
	public Integer getWbzlwtflCount(Date date, Date d, Company comp,
			String issue) {
		Query q = getEntityManager().createQuery(
				"select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"sub_issue_type = :issue ");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("issue", issue);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getWbzlwtflCount(Date date, Company comp, String issue) {
		Query q = getEntityManager().createQuery("select count(*) from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = :issue");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", date);
		q.setParameter("issue", issue);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public List<WbyclzlwtEntity> getAll(Date d, Company comp) {
		Query q = getEntityManager().createQuery(
				"from WbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 ");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", d);
		return q.getResultList();
	}
}
