package com.tbea.ic.operation.model.dao.xnych.xnych;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.xnych.XnychEntity;



@Repository(XnychDaoImpl.NAME)
@Transactional("transactionManager")
public class XnychDaoImpl extends AbstractReadWriteDaoImpl<XnychEntity> implements XnychDao {
	public final static String NAME = "XnychDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XnychEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XnychEntity where nf=:nf and yf=:yf and dwid=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
}
