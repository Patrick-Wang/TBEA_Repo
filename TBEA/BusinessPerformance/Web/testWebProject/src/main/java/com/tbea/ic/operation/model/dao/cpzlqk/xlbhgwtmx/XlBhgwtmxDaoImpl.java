package com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx;


import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(XlBhgwtmxDaoImpl.NAME)
@Transactional("transactionManager")
public class XlBhgwtmxDaoImpl extends AbstractReadWriteDaoImpl<XlBhgwtmxEntity> implements XlBhgwtmxDao {
	public final static String NAME = "XlBhgwtmxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XlBhgwtmxEntity> getByDate(Date d) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from XlBhgwtmxEntity where nf = :nf and yf = :yf");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		return q.getResultList();
	}
}
