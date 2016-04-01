package com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YhjzllEntity;



@Repository(TksDaoImpl.NAME)
@Transactional("transactionManager")
public class TksDaoImpl extends AbstractReadWriteDaoImpl<TksEntity> implements TksDao {
	public final static String NAME = "TksDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<TksEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from TksEntity where date >= :start and date <= :end order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public TksEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from TksEntity where date = :date");
		q.setParameter("date", date);
		List<TksEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
