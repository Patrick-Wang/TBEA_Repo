package com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;



@Repository(ZhbDaoImpl.NAME)
@Transactional("transactionManager")
public class ZhbDaoImpl extends AbstractReadWriteDaoImpl<ZhbEntity> implements ZhbDao {
	public final static String NAME = "ZhbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}


	@Override
	public List<ZhbEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from ZhbEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public ZhbEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from ZhbEntity where date = :date");
		q.setParameter("date", date);
		List<ZhbEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
