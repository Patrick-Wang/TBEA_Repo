package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LzbbEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.MyzsEntity;



@Repository(LzbbDaoImpl.NAME)
@Transactional("transactionManager")
public class LzbbDaoImpl extends AbstractReadWriteDaoImpl<LzbbEntity> implements LzbbDao {
	public final static String NAME = "LzgbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<LzbbEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from LzbbEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public LzbbEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from LzbbEntity where date = :date");
		q.setParameter("date", date);
		List<LzbbEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
