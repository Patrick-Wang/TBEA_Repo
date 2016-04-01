package com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PVCSzEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PmiCpiPpiEntity;



@Repository(PmiCpiPpiDaoImpl.NAME)
@Transactional("transactionManager")
public class PmiCpiPpiDaoImpl extends AbstractReadWriteDaoImpl<PmiCpiPpiEntity> implements PmiCpiPpiDao {
	public final static String NAME = "PmiCpiPpiDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<PmiCpiPpiEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from PmiCpiPpiEntity where date >= :start and date <= :end");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public PmiCpiPpiEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from PmiCpiPpiEntity where date = :date");
		q.setParameter("date", date);
		List<PmiCpiPpiEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
