package com.tbea.ic.operation.model.dao.cqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.XLNWFKFS;
import com.tbea.ic.operation.model.entity.local.CQK;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;


@Repository
@Transactional("transactionManager2")
public class CQKDaoImpl extends AbstractReadWriteDaoImpl<CQK> implements
		CQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CQK> getPreYearCQK(Date d, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Calendar preYear = Calendar.getInstance();
        preYear.set(cal.get(Calendar.YEAR) - 1, 1, 1);
        
        Calendar preYearMonth = Calendar.getInstance();
        preYearMonth.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
        
        Query q = getEntityManager().createQuery("select c from CQK c where c.ny >= ?1 and c.ny <= ?2 and c.qybh = ?3");
		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		q.setParameter(3, comp.getId());
		
		return q.getResultList();
	}


	@Override
	public List<CQK> getCurYearCQK(Date d, Company comp) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Calendar curYear = Calendar.getInstance();
        curYear.set(cal.get(Calendar.YEAR), 0, 1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        
   
        Query q = getEntityManager().createQuery("select c from CQK c where c.ny >= ?1 and c.ny <= ?2 and c.qybh = ?3");
		q.setParameter(1, Util.format(curYear.getTime()));
		q.setParameter(2, Util.format(d));
		q.setParameter(3, comp.getId());
		
		return q.getResultList();
	}


	@Override
	public List<CQK> getCqkData(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select c from CQK c where c.ny = ?1 and c.qybh = ?2");
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
		q.setParameter(1, Util.format(d));
		q.setParameter(2, comp.getId());
		return  q.getResultList();
	}


	@Override
	public CQK getLatestCQK() {
		Query q = getEntityManager().createQuery(
				"from CQK order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<CQK> cqks = q.getResultList();
		if (!cqks.isEmpty()){
			return cqks.get(0);
		}
		return null;
	}


	@Override
	public List<CQK> getCqkData(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select c from CQK c where c.ny = :date and c.qybh in (" + Util.toString(comps) + ")");
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
		q.setParameter("date", Util.format(d));
		return  q.getResultList();
	}


	@Override
	public List<CQK> getCurYearCQK(Date d, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Calendar curYear = Calendar.getInstance();
        curYear.set(cal.get(Calendar.YEAR), 0, 1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        Query q = getEntityManager().createQuery("select c from CQK c where c.ny >= ?1 and c.ny <= ?2 and c.qybh in (" + Util.toString(comps) + ")");
		q.setParameter(1, Util.format(curYear.getTime()));
		q.setParameter(2, Util.format(d));

		return q.getResultList();
	}


	@Override
	public List<CQK> getPreYearCQK(Date d, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Calendar preYear = Calendar.getInstance();
        preYear.set(cal.get(Calendar.YEAR) - 1, 1, 1);
        Calendar preYearMonth = Calendar.getInstance();
        preYearMonth.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH), 1);
        Query q = getEntityManager().createQuery("select c from CQK c where c.ny >= ?1 and c.ny <= ?2 and c.qybh in (" + Util.toString(comps) + ")");
		q.setParameter(1, Util.format(preYear.getTime()));
		q.setParameter(2, Util.format(preYearMonth.getTime()));
		return q.getResultList();
	}

}
