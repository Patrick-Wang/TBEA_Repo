package com.tbea.ic.operation.model.dao.blhtdqqkhz;

import java.sql.Date;
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
import com.tbea.ic.operation.model.entity.BLHTDQQKHZ;
import com.tbea.ic.operation.model.entity.ZTYSZKFX;

@Repository
@Transactional("transactionManager")
public class BLHTDQQKHZDaoImpl extends AbstractReadWriteDaoImpl<BLHTDQQKHZ> implements BLHTDQQKHZDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BLHTDQQKHZ> getBlAfterDate(Calendar cal, Company comp) {
		Query q = getEntityManager().createQuery(
				"select b from BLHTDQQKHZ b where b.ny >= ?1 and b.qybh = ?2");
		String date = Util.format(cal.getTime());
		q.setParameter(1, date);
		q.setParameter(2, comp.getId());
		return q.getResultList();
	}

	@Override
	public List<BLHTDQQKHZ> getBltbbh(Calendar cal, Company comp) {
		Calendar preYear = Calendar.getInstance();
		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);

		Calendar preYearMonth = Calendar.getInstance();
		preYearMonth.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));

		Calendar curYear = Calendar.getInstance();
		curYear.set(cal.get(Calendar.YEAR), 0, 1);

		Query q = getEntityManager()
				.createQuery(
						"select b from BLHTDQQKHZ b where b.ny >= ?1 and b.ny <= ?2 or b.ny >= ?3 and b.ny <= ?4 and b.qybh = ?5");
		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		q.setParameter(3, Util.format(curYear.getTime()));
		q.setParameter(4, Util.format(cal.getTime()));
		q.setParameter(5, comp.getId());
		return q.getResultList();
	}

	@Override
	public BLHTDQQKHZ getLatestBl(Date d) {
		Query q = getEntityManager().createQuery(
				"from BLHTDQQKHZ where Ny <= :date order by Ny desc");
		q.setParameter("date", Util.format(d));
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<BLHTDQQKHZ> bls = q.getResultList();
		if (!bls.isEmpty()){
			return bls.get(0);
		}
		return null;
	}

}
