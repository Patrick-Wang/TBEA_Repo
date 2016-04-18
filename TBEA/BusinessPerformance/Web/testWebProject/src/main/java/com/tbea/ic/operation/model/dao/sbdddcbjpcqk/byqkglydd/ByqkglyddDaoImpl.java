package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.controller.servlet.wlydd.sbdddcbjpcqk.KglyddType;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.ByqkglyddEntity;



@Repository(ByqkglyddDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqkglyddDaoImpl extends AbstractReadWriteDaoImpl<ByqkglyddEntity> implements ByqkglyddDao {
	public final static String NAME = "KglyddbhDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ByqkglyddEntity> getByDate(Date d, KglyddType type) {
		Query q = this.getEntityManager().createQuery("from ByqkglyddEntity where nf=:nf and yf=:yf and type=:type");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("type", type.ordinal());
		return q.getResultList();
	}
}
