package com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;
import com.xml.frame.report.util.EasyCalendar;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;



@Repository(XlBhgwtmxDaoImpl.NAME)
@Transactional("transactionManager")
public class XlBhgwtmxDaoImpl extends AbstractReadWriteDaoImpl<XlBhgwtmxEntity> implements XlBhgwtmxDao {
	public final static String NAME = "XlBhgwtmxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XlBhgwtmxEntity> getByDate(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from XlBhgwtmxEntity where nf = :nf and yf = :yf");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
//		q.setParameter("zt", zts);
		return q.getResultList();
	}

	@Override
	public XlBhgwtmxEntity getFirstBhgwtmx(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from XlBhgwtmxEntity where nf = :nf and  yf = :yf and dwid = :dwid");
        q.setFirstResult(0);
        q.setMaxResults(1);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		List<XlBhgwtmxEntity> result = q.getResultList();
		if (result.isEmpty()){
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<XlBhgwtmxEntity> getByDate(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from XlBhgwtmxEntity where  nf = :nf and  yf = :yf and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}

	@Override
	public List<XlBhgwtmxEntity> getByDate(Date d, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from XlBhgwtmxEntity where nf = :nf and  yf = :yf and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
//		q.setParameter("zt", zts);
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}
}
