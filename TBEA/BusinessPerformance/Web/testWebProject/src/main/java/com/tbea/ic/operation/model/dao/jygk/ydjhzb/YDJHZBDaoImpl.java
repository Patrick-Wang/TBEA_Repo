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
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf >= :nStart and nf <= :nEnd and yf >= :yStart and yf <= :yEnd and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nStart", calStart.get(Calendar.YEAR));
		q.setParameter("nEnd", calEnd.get(Calendar.YEAR));
		q.setParameter("yStart", calStart.get(Calendar.MONTH) + 1);
		q.setParameter("yEnd", calEnd.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	

}
