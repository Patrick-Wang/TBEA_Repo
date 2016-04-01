package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LzbbEntity;



@Repository(LwgDaoImpl.NAME)
@Transactional("transactionManager")
public class LwgDaoImpl extends AbstractReadWriteDaoImpl<LwgEntity> implements LwgDao {
	public final static String NAME = "LwgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<LwgEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from LwgEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public LwgEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from LwgEntity where date = :date");
		q.setParameter("date", date);
		List<LwgEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
