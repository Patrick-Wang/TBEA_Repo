package com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml;


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
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;



@Repository(CpdlmlDaoImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlDaoImpl extends AbstractReadWriteDaoImpl<CpdlmlEntity> implements CpdlmlDao {
	public final static String NAME = "CpdlmlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public CpdlmlEntity getByDate(Date d, Integer cpid, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from CpdlmlEntity where nf=:nf and yf=:yf and cpdl = :cpid and dwid = :dwid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", comp.getId());
		List<CpdlmlEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public CpdlmlEntity getSumByDate(Date d, Integer cpid, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("select sum(ljcb) as cb, sum(ljsr) as sr from CpdlmlEntity where nf=:nf and yf=:yf and cpdl = :cpid and dwid in :dwids");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("cpid", cpid);
		q.setParameter("dwids", Util.toIds(comps));
		List<Object[]> list = q.getResultList();
		CpdlmlEntity entity = new CpdlmlEntity();
		entity.setNf(cal.get(Calendar.YEAR));
		entity.setYf(cal.get(Calendar.MONTH) + 1);
		entity.setCpdl(cpid);
		entity.setLjcb((Double)list.get(0)[0]);
		entity.setLjsr((Double)list.get(0)[1]);
		return entity;
	}
}
