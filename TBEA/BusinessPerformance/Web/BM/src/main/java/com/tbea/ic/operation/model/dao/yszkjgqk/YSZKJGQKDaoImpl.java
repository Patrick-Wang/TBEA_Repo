package com.tbea.ic.operation.model.dao.yszkjgqk;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.XLNWFKFS;
import com.tbea.ic.operation.model.entity.YSZKJGQK;

@Repository
@Transactional("transactionManager")
public class YSZKJGQKDaoImpl extends AbstractReadWriteDaoImpl<YSZKJGQK>
		implements YSZKJGQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YSZKJGQK> getYszkjg(Calendar cal, Company comp) {
		Query q = getEntityManager().createQuery(
				"select y from YSZKJGQK y where y.ny = ?1 and y.qybh = ?2");
		q.setParameter(1, Util.format(cal.getTime()));
		q.setParameter(2, comp.getId());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YSZKJGQK> getJetbbh(Calendar cal, Company comp) {
		Query q = getEntityManager()
				.createQuery(
						"select y from YSZKJGQK y where y.ny >= ?1 and y.ny <= ?2 or y.ny >= ?3 and y.ny <= ?4 and y.qybh = ?5");
		Calendar preYear = Calendar.getInstance();
		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);
		Calendar preYearMonth = Calendar.getInstance();
		preYearMonth
				.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
		Calendar curYear = Calendar.getInstance();
		curYear.set(cal.get(Calendar.YEAR), 0, 1);

		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		q.setParameter(3, Util.format(curYear.getTime()));
		q.setParameter(4, Util.format(cal.getTime()));
		q.setParameter(5, comp.getId());
		return q.getResultList();

	}

	@Override
	public List<YSZKJGQK> getWdqtbbh(Calendar cal, Company comp) {
		Query q = getEntityManager()
				.createQuery(
						"select y from YSZKJGQK y where y.ny >= ? and y.ny <= ? or y.ny >= ? and y.ny <= ? and y.qybh = ?");
		Calendar preYear = Calendar.getInstance();
		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);
		Calendar preYearMonth = Calendar.getInstance();
		preYearMonth
				.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
		Calendar curYear = Calendar.getInstance();
		curYear.set(cal.get(Calendar.YEAR), 0, 1);

		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		q.setParameter(3, Util.format(curYear.getTime()));
		q.setParameter(4, Util.format(cal.getTime()));
		q.setParameter(5, comp.getId());
		return q.getResultList();
	}

	@Override
	public boolean hasCompany(Company comp) {
		Query q = getEntityManager()
				.createQuery("from YSZKJGQK where qybh = :comp");
		q.setParameter("comp", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}

	@Override
	public YSZKJGQK getLatestYszkjg() {
		Query q = getEntityManager().createQuery(
				"from YSZKJGQK order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YSZKJGQK> yszks = q.getResultList();
		if (!yszks.isEmpty()){
			return yszks.get(0);
		}
		return null;
	}

	@Override
	public List<YSZKJGQK> getYszkjg(Calendar cal, List<Company> comps) {
		Query q = getEntityManager().createQuery(
				"select y from YSZKJGQK y where y.ny = :date and y.qybh in (" + Util.toString(comps) + ")");
		q.setParameter("date", Util.format(cal.getTime()));
		return q.getResultList();
	}

	@Override
	public List<YSZKJGQK> getWdqtbbh(Calendar cal, List<Company> comps) {
		Query q = getEntityManager()
				.createQuery(
						"select y from YSZKJGQK y where y.ny >= :preYear and y.ny <= :preYearMonth or y.ny >= :curYear and y.ny <= :curYearMonth and y.qybh in (" + Util.toString(comps) + ")");
		Calendar preYear = Calendar.getInstance();
		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);
		Calendar preYearMonth = Calendar.getInstance();
		preYearMonth.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
		Calendar curYear = Calendar.getInstance();
		curYear.set(cal.get(Calendar.YEAR), 0, 1);

		q.setParameter("preYear", Util.format(preYear.getTime()));
		q.setParameter("preYearMonth", Util.format(preYearMonth.getTime()));
		q.setParameter("curYear", Util.format(curYear.getTime()));
		q.setParameter("curYearMonth", Util.format(cal.getTime()));

		return q.getResultList();

	}

	@Override
	public List<YSZKJGQK> getJetbbh(Calendar cal, List<Company> comps) {
		Query q = getEntityManager()
				.createQuery(
						"select y from YSZKJGQK y where y.ny >= ?1 and y.ny <= ?2 or y.ny >= ?3 and y.ny <= ?4 and y.qybh in (" + Util.toString(comps) + ")");
		Calendar preYear = Calendar.getInstance();
		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);
		Calendar preYearMonth = Calendar.getInstance();
		preYearMonth
				.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
		Calendar curYear = Calendar.getInstance();
		curYear.set(cal.get(Calendar.YEAR), 0, 1);

		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		q.setParameter(3, Util.format(curYear.getTime()));
		q.setParameter(4, Util.format(cal.getTime()));
		return q.getResultList();
	}

}
