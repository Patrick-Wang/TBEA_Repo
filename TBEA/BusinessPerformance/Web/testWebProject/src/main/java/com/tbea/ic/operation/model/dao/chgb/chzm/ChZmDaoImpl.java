package com.tbea.ic.operation.model.dao.chgb.chzm;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChZmDaoImpl.NAME)
@Transactional("transactionManager")
public class ChZmDaoImpl extends AbstractReadWriteDaoImpl<ChZmEntity> implements ChZmDao {
	public final static String NAME = "ChZmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ChZmEntity> getByDate(Date d, Company company) {
		Query q = this.getEntityManager().createQuery("from ChZmEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
}
