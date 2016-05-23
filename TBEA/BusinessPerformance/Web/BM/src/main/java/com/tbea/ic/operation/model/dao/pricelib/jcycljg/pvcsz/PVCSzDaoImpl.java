package com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PVCSzEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;



@Repository(PVCSzDaoImpl.NAME)
@Transactional("transactionManager")
public class PVCSzDaoImpl extends AbstractReadWriteDaoImpl<PVCSzEntity> implements PVCSzDao {
	public final static String NAME = "PVCSzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<PVCSzEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from PVCSzEntity where date >= :start and date <= :end order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public PVCSzEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from PVCSzEntity where date = :date");
		q.setParameter("date", date);
		List<PVCSzEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
