package com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.MyzsEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PmiCpiPpiEntity;



@Repository(MyzsDaoImpl.NAME)
@Transactional("transactionManager")
public class MyzsDaoImpl extends AbstractReadWriteDaoImpl<MyzsEntity> implements MyzsDao {
	public final static String NAME = "MyzsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	

	@Override
	public List<MyzsEntity> getEntities(Date start, Date end) {
		Query q = this.getEntityManager().createQuery("from MyzsEntity where DateDiff(mm, date, :start) <= 0 and DateDiff(mm, date, :end) >= 0 order by date asc");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public MyzsEntity getByDate(Date date) {
		Query q = this.getEntityManager().createQuery("from MyzsEntity where DateDiff(mm, date, :date) = 0");
		q.setParameter("date", date);
		List<MyzsEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
	
}
