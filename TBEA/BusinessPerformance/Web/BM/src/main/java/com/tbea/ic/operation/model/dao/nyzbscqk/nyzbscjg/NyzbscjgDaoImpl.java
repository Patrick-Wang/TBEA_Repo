package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscjgEntity;



@Repository(NyzbscjgDaoImpl.NAME)
@Transactional("transactionManager")
public class NyzbscjgDaoImpl extends AbstractReadWriteDaoImpl<NyzbscjgEntity> implements NyzbscjgDao {
	public final static String NAME = "NyzbscjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<NyzbscjgEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscjgEntity where dwid=:dwid and nf=:nf and yf=:yf");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<NyzbscjgEntity> getByYear(Date d, Company company, int kq,
			int mz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscjgEntity where dwid=:dwid and nf=:nf and kq=:kq and mz=:mz");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("mz", mz);
		q.setParameter("kq", kq);
		return q.getResultList();
	}

	@Override
	public NyzbscjgEntity getByDate(Date d, Company company, int kq, int mz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NyzbscjgEntity where dwid=:dwid and nf=:nf and yf=:yf and kq=:kq and mz=:mz");
		q.setParameter("dwid", company.getId());
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("mz", mz);
		q.setParameter("kq", kq);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (NyzbscjgEntity) ret.get(0);
	}
}
