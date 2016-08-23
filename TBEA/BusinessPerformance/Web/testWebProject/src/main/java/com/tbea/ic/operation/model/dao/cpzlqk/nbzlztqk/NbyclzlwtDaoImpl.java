package com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk;


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
import com.tbea.ic.operation.model.entity.cpzlqk.NbyclzlwtEntity;



@Repository(NbyclzlwtDaoImpl.NAME)
@Transactional("transactionManager")
public class NbyclzlwtDaoImpl extends AbstractReadWriteDaoImpl<NbyclzlwtEntity> implements NbyclzlwtDao {
	public final static String NAME = "NbyclzlwtDaoImpl";

	@Override
	public Integer getCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getGyzlwtCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '工艺质量问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getSczzzlqkCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '生产制造质量问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
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
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"sub_issue_type = '设计问题'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程'");
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测'");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date date, Date d, Company company) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测'");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date date, Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测'");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测'");
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
				"select responsibility_department from NbyclzlwtEntity where company_name = :comp " +
				"group by responsibility_department");
		q.setParameter("comp", company.getName());
		return q.getResultList();
	}
	
	

	@Override
	public Integer getSczzzlqkxxxxCount(Date d, Company comp, String gs) {
		Query q = getEntityManager().createQuery(
				"select count(*) from NbyclzlwtEntity where company_name = :comp and " +
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
				"select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"responsibility_department = :gs ");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("gs", gs);
		return ((Long)q.getResultList().get(0)).intValue();
	}
}
