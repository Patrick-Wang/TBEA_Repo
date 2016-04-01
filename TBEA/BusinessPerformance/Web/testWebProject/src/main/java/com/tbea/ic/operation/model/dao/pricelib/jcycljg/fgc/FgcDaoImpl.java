package com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.FgcEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;



@Repository(FgcDaoImpl.NAME)
@Transactional("transactionManager")
public class FgcDaoImpl extends AbstractReadWriteDaoImpl<FgcEntity> implements FgcDao {
	public final static String NAME = "FgcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<FgcEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from FgcEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public FgcEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from FgcEntity where date = :date");
		q.setParameter("date", date);
		List<FgcEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
