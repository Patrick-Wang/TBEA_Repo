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
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;



@Repository(GgpDaoImpl.NAME)
@Transactional("transactionManager")
public class GgpDaoImpl extends AbstractReadWriteDaoImpl<GgpEntity> implements GgpDao {
	public final static String NAME = "GgpDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<GgpEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from GgpEntity where DateDiff(mm, date, :start) <= 0 and DateDiff(mm, date, :end) >= 0 order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public GgpEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from GgpEntity where DateDiff(mm, date, :date) = 0");
		q.setParameter("date", date);
		List<GgpEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
