package com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;



@Repository(JkzjDaoImpl.NAME)
@Transactional("transactionManager")
public class JkzjDaoImpl extends AbstractReadWriteDaoImpl<JkzjEntity> implements JkzjDao {
	public final static String NAME = "JkzjDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JkzjEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from JkzjEntity where date >= :start and date <= :end order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public JkzjEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from JkzjEntity where date = :date");
		q.setParameter("date", date);
		List<JkzjEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}