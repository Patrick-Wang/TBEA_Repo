package com.tbea.test.testWebProject.model.dao.yszkjgqk;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.YSZKJGQK;

@Repository
@Transactional("transactionManager2")
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

}
