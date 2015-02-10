package com.tbea.ic.operation.model.dao.jygk.sjzb;

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
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.SJZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class SJZBDaoImpl extends AbstractReadWriteDaoImpl<SJZB> implements SJZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public SJZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<SJZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<SJZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<SJZB> getZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<SJZB> getUnapprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and sjshzt.id = :id and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("id", 2);
		return q.getResultList();
	}

	@Override
	public List<SJZB> getApprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and sjshzt.id = :id and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("id", 1);
		return q.getResultList();
	}

	

}
