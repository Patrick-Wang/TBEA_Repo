package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;



@Repository(GgpDaoImpl.NAME)
@Transactional("transactionManager")
public class GgpDaoImpl extends AbstractReadWriteDaoImpl<GgpEntity> implements GgpDao {
	public final static String NAME = "GgpDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<GgpEntity> getGgp(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from GgpEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}
}
