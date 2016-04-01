package com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.FgcEntity;



@Repository(EVADaoImpl.NAME)
@Transactional("transactionManager")
public class EVADaoImpl extends AbstractReadWriteDaoImpl<EVAEntity> implements EVADao {
	public final static String NAME = "EVADaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<EVAEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from EVAEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public EVAEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from EVAEntity where date = :date");
		q.setParameter("date", date);
		List<EVAEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
