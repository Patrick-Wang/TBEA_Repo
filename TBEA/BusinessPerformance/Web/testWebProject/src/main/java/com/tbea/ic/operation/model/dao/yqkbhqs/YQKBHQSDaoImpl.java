package com.tbea.ic.operation.model.dao.yqkbhqs;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YQKBHQS;
import com.util.tools.DateUtil;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

@Repository
@Transactional("transactionManager2")
public class YQKBHQSDaoImpl extends AbstractReadWriteDaoImpl<YQKBHQS> implements
		YQKBHQSDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cur, Company comp) {
		Query q = getEntityManager().createQuery(
				"select y from YQKBHQS y where y.ny >= ?1 and y.ny <= ?2 and y.qybh = ?3");
		Calendar yearBegin = Calendar.getInstance();
		yearBegin.set(cur.get(Calendar.YEAR), 0, 1);
		Calendar yearEnd = Calendar.getInstance();
		yearEnd.set(cur.get(Calendar.YEAR), 11, 1);
		String timeBegin = DateUtil.month1(yearBegin.getTime());
		String timeEnd = DateUtil.month1(yearEnd.getTime());
		q.setParameter(1, timeBegin);
		q.setParameter(2, timeEnd);
		q.setParameter(3, comp.getId());
		return q.getResultList();
	}

	@Override
	public YQKBHQS getLatestDate() {
		Query q = getEntityManager().createQuery(
				"from YQKBHQS order by Ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YQKBHQS> yqks = q.getResultList();
		if (!yqks.isEmpty()){
			return yqks.get(0);
		}
		return null;
	}

	@Override
	public List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cur,
			List<Company> comps) {
		 

		Query q = getEntityManager().createQuery(
				"select y from YQKBHQS y where y.ny >= ?1 and y.ny <= ?2 and y.qybh in (" + Util.toString(comps) + ")");
		Calendar yearBegin = Calendar.getInstance();
		yearBegin.set(cur.get(Calendar.YEAR), 0, 1);
		Calendar yearEnd = Calendar.getInstance();
		yearEnd.set(cur.get(Calendar.YEAR), 11, 1);
		String timeBegin = DateUtil.month1(yearBegin.getTime());
		String timeEnd = DateUtil.month1(yearEnd.getTime());
		q.setParameter(1, timeBegin);
		q.setParameter(2, timeEnd);
		return q.getResultList();
	}

}
