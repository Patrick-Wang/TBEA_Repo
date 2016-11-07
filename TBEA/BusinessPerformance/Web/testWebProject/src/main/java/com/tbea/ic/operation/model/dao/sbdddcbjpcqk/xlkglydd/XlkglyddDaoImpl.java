package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd;


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
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.XlkglyddEntity;



@Repository(XlkglyddDaoImpl.NAME)
@Transactional("transactionManager")
public class XlkglyddDaoImpl extends AbstractReadWriteDaoImpl<XlkglyddEntity> implements XlkglyddDao {
	public final static String NAME = "KglyddDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XlkglyddEntity> getByDate(Date d, WlyddType type, Company comp) {
		Query q = this.getEntityManager().createQuery("from XlkglyddEntity where nf=:nf and yf=:yf and type=:type and dwid = :dwid");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("type", type.ordinal());
		q.setParameter("dwid", comp.getId());
		return q.getResultList();
	}

	@Override
	public XlkglyddEntity getKglydd(Date d, WlyddType type, String sclx,
			Company comp) {
		Query q = this.getEntityManager().createQuery("from XlkglyddEntity where nf=:nf and yf=:yf and type=:type and dwid = :dwid and sclx = :sclx");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("type", type.ordinal());
		q.setParameter("sclx", sclx);
		q.setParameter("dwid", comp.getId());
		List ret = q.getResultList();
		if (!ret.isEmpty()){
			return (XlkglyddEntity) ret.get(0);
		}
		return null;
	}
}
