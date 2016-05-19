package com.tbea.ic.operation.model.dao.yqysysfx;

import java.sql.Date;
import java.util.List;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YQYSYSFX;
//import com.tbea.ic.operation.model.entity.local.YQK;

@Repository
@Transactional("transactionManager")
public class YQYSYSFXDaoImpl extends AbstractReadWriteDaoImpl<YQYSYSFX> implements YQYSYSFXDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YQYSYSFX> getYqysysfxList(Date d, Company comp) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		Query q = this.getEntityManager().createQuery("select y from YQYSYSFX y where y.qybh = :id and y.nf = :year and y.yf = :month");
		q.setParameter("id", comp.getId());
		q.setParameter("year", year+"");
		q.setParameter("month", month+"");
		return q.getResultList();
	}

	@Override
	public List<YQYSYSFX> getYqysysfxList(Date d, List<Company> comps) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		Query q = this.getEntityManager().createQuery("select y from YQYSYSFX y where y.qybh in (" + Util.toString(comps) + ") and y.nf = :year and y.yf = :month");
		q.setParameter("year", year+"");
		q.setParameter("month", month+"");
		return q.getResultList();
	}

}
