package com.tbea.ic.operation.model.dao.chgb.chjykc;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChJykcDaoImpl.NAME)
@Transactional("transactionManager")
public class ChJykcDaoImpl extends AbstractReadWriteDaoImpl<ChJykcEntity> implements ChJykcDao {
	public final static String NAME = "ChJykcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ChJykcEntity> getByDate(Date d, Company company) {
		Query q = this.getEntityManager().createQuery("from ChJykcEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
}
