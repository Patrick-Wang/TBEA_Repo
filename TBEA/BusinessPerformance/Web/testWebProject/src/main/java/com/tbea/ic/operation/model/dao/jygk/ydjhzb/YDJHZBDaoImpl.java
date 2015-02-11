package com.tbea.ic.operation.model.dao.jygk.ydjhzb;

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
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class YDJHZBDaoImpl extends AbstractReadWriteDaoImpl<YDJHZB> implements YDJHZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public YDJHZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<YDJHZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<YDJHZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<YDJHZB> getZb(List<Company> comps, Date dStart, Date dEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		Query q = this.getEntityManager().createQuery("from YDJHZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("dStart",dStart);
		q.setParameter("dEnd", dEnd);
		return q.getResultList();
	}

	@Override
	public List<YDJHZB> getUnapprovedZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp and ydjhshzt.id = 2");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<YDJHZB> getApprovedZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp and ydjhshzt.id = 1");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from YDJHZB");
		return q.getResultList();
	}

	

}
