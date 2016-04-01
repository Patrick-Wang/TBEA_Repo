package com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;



@Repository(JtDaoImpl.NAME)
@Transactional("transactionManager")
public class JtDaoImpl extends AbstractReadWriteDaoImpl<JtEntity> implements JtDao {
	public final static String NAME = "JtDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JtEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from JtEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public JtEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from JtEntity where date = :date");
		q.setParameter("date", date);
		List<JtEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
