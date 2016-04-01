package com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GxEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;



@Repository(GxDaoImpl.NAME)
@Transactional("transactionManager")
public class GxDaoImpl extends AbstractReadWriteDaoImpl<GxEntity> implements GxDao {
	public final static String NAME = "GxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<GxEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from GxEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public GxEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from GxEntity where date = :date");
		q.setParameter("date", date);
		List<GxEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
