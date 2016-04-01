package com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GxEntity;



@Repository(GjyyDaoImpl.NAME)
@Transactional("transactionManager")
public class GjyyDaoImpl extends AbstractReadWriteDaoImpl<GjyyEntity> implements GjyyDao {
	public final static String NAME = "GjyyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<GjyyEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from GjyyEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public GjyyEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from GjyyEntity where date = :date");
		q.setParameter("date", date);
		List<GjyyEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
