package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs;


import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YsjsDaoImpl.NAME)
@Transactional("transactionManager")
public class YsjsDaoImpl extends AbstractReadWriteDaoImpl<YsjsEntity> implements YsjsDao {
	public final static String NAME = "YsjsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YsjsEntity> getYsjs(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from YsjsEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}
}
