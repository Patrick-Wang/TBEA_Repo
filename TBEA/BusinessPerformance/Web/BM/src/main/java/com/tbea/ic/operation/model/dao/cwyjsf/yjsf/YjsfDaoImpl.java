package com.tbea.ic.operation.model.dao.cwyjsf.yjsf;


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
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfEntity;
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfNdqcsEntity;



@Repository(YjsfDaoImpl.NAME)
@Transactional("transactionManager")
public class YjsfDaoImpl extends AbstractReadWriteDaoImpl<YjsfEntity> implements YjsfDao {
	public final static String NAME = "YjsfDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YjsfEntity> getByYear(Date d, Company company, Integer sz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Query q = this.getEntityManager().createQuery("from YjsfEntity where nf=:nf and dwid=:compId and sz = :sz)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("sz", sz);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YjsfEntity getByDate(Date d, Company company, Integer sz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Query q = this.getEntityManager().createQuery("from YjsfEntity where nf=:nf and yf=:yf and dwid=:compId and sz = :sz)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("sz", sz);
		q.setParameter("compId", company.getId());
		List<YjsfEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}