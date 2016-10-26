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
	public Integer getCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getGyzlwtCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '工艺质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getGyzlwtCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '工艺质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getGyzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '工艺质量问题' and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getGyzlwtCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '工艺质量问题' and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}
	
	@Override
	public Integer getSczzzlqkCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '生产制造质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '生产制造质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '生产制造质量问题' and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '生产制造质量问题' and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '设计质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '设计质量问题' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '设计质量问题' and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSjzlqkCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '设计质量问题' and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程' and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlCnzzwtCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '生产过程' and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date date, Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测' and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测' and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYclzlRcjcwtCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = '原材料质量问题' and " +
				"material_happen_phase = '入场检测' and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<String> getXmgs(Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select responsibility_department from NbyclzlwtEntity where company_name = :comp and " + 
				"zt in :zts " +
				"group by responsibility_department ");
		q.setParameter("comp", company.getName());
		q.setParameter("zts", zts);
		return q.getResultList();
	}
	
	

	@Override
	public Integer getSczzzlqkxxxxCount(Date d, Company comp, String gs, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"responsibility_department = :gs  and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", d);
		q.setParameter("gs", gs);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getSczzzlqkxxxxCount(Date date, Date d, Company comp, String gs, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"responsibility_department = :gs  and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("gs", gs);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date date, Date d, Company comp, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type in ('安装问题','运输问题') and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date date, Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type in ('安装问题','运输问题') and " + 
				"zt in :zts");
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date d, Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type in ('安装问题','运输问题') and " + 
				"zt in :zts");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getYsazzlwtCount(Date d, List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ") and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type in ('安装问题','运输问题') and " + 
				"zt in :zts");
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public List<String> getIssues(List<Company> comps, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select issue_type from NbyclzlwtEntity where company_name in (" +
				Util.toNameString(comps) + ")  and " + 
				"zt in :zts " +
				"group by issue_type");
		q.setParameter("zts", zts);
		return q.getResultList();
	}
	
	@Override
	public List<String> getIssues(Company company, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select issue_type from NbyclzlwtEntity where company_name = :comp  and " + 
				"zt in :zts " +
				"group by issue_type");
		q.setParameter("comp", company.getName());
		q.setParameter("zts", zts);
		return q.getResultList();
	}

	@Override
	public Integer getNbzlwtflCount(Date date, Date d, Company comp,
			String issue, List<Integer> zts) {
		Query q = getEntityManager().createQuery(
				"select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"dateDiff(mm, issue_happen_date, :dStart) <= 0 and " +
				"dateDiff(mm, issue_happen_date, :dEnd) >= 0 and " + 
				"issue_type = :issue  and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("dStart", date);
		q.setParameter("dEnd", d);
		q.setParameter("issue", issue);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public Integer getNbzlwtflCount(Date date, Company comp, String issue, List<Integer> zts) {
		Query q = getEntityManager().createQuery("select count(*) from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0 and " + 
				"issue_type = :issue and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", date);
		q.setParameter("issue", issue);
		q.setParameter("zts", zts);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public List<NbyclzlwtEntity> getAll(Date d, Company comp, List<Integer> zts) {
		Query q = getEntityManager().createQuery("from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0  and " + 
				"zt in :zts");
		q.setParameter("comp", comp.getName());
		q.setParameter("date", d);
		q.setParameter("zts", zts);
		return q.getResultList();
	}

	@Override
	public List<NbyclzlwtEntity> getAllIgnoreStatus(Date d, Company company) {
		Query q = getEntityManager().createQuery("from NbyclzlwtEntity where company_name = :comp and " +
				"datediff(mm, issue_happen_date, :date) = 0");
		q.setParameter("comp", company.getName());
		q.setParameter("date", d);
		return q.getResultList();
	}
}
