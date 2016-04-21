package com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.NychEntity;
import com.tbea.ic.operation.model.entity.dzwzgb.DzclkcbEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(DzclkcbDaoImpl.NAME)
@Transactional("transactionManager")
public class DzclkcbDaoImpl extends AbstractReadWriteDaoImpl<DzclkcbEntity> implements DzclkcbDao {
	public final static String NAME = "DzclkcbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<DzclkcbEntity> getByNf(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Query q = this.getEntityManager().createQuery("from DzclkcbEntity where nf=:nf and dwid=:compId)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("compId", company.getId());
		
		return q.getResultList();
	}

	@Override
	public List<DzclkcbEntity> getByNy(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Query q = this.getEntityManager().createQuery("from DzclkcbEntity where nf=:nf and yf = :yf and dwid=:compId)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
}