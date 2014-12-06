package com.tbea.test.testWebProject.model.dao.cqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.entity.local.CQK;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;


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

}
