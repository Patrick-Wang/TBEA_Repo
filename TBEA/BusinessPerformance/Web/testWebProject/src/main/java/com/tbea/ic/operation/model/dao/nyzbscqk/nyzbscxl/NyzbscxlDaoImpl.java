package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscxl;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscxlEntity;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyCompMiningAreaMatchEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscxl.NyzbscxlDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NyzbscxlDaoImpl.NAME)
@Transactional("transactionManager")
public class NyzbscxlDaoImpl extends AbstractReadWriteDaoImpl<NyzbscxlEntity> implements NyzbscxlDao {
	public final static String NAME = "NyzbscxlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}


	@Override
	public List<NyzbscxlEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscxlEntity where dwid=:dwid and nf=:nf and yf=:yf");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<NyzbscxlEntity> getByYear(Date d, Company company, int kq,
			int mz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscxlEntity where dwid=:dwid and nf=:nf and kq=:kq and mz=:mz");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("mz", mz);
		q.setParameter("kq", kq);
		return q.getResultList();
	}

	@Override
	public NyzbscxlEntity getByDate(Date d, Company company, int kq, int mz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscxlEntity where dwid=:dwid and nf=:nf and yf=:yf and kq=:kq and mz=:mz");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("mz", mz);
		q.setParameter("kq", kq);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (NyzbscxlEntity) ret.get(0);
	}
}
