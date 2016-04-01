package com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;



@Repository(DmdjyxDaoImpl.NAME)
@Transactional("transactionManager")
public class DmdjyxDaoImpl extends AbstractReadWriteDaoImpl<DmdjyxEntity> implements DmdjyxDao {
	public final static String NAME = "DmdjyxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<DmdjyxEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from DmdjyxEntity where date >= :start and date <= :end order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public DmdjyxEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from DmdjyxEntity where date = :date");
		q.setParameter("date", date);
		List<DmdjyxEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
