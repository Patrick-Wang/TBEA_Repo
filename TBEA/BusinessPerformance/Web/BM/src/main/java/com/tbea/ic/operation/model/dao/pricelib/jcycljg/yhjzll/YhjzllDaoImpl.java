package com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YhjzllEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;



@Repository(YhjzllDaoImpl.NAME)
@Transactional("transactionManager")
public class YhjzllDaoImpl extends AbstractReadWriteDaoImpl<YhjzllEntity> implements YhjzllDao {
	public final static String NAME = "YhjzllDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YhjzllEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from YhjzllEntity where date >= :start and date <= :end order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public YhjzllEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from YhjzllEntity where date = :date");
		q.setParameter("date", date);
		List<YhjzllEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
