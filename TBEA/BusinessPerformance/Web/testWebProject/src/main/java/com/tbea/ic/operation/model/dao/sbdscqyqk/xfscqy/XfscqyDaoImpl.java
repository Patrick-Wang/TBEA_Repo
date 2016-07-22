package com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfscqyEntity;



@Repository(XfscqyDaoImpl.NAME)
@Transactional("transactionManager")
public class XfscqyDaoImpl extends AbstractReadWriteDaoImpl<XfscqyEntity> implements XfscqyDao {
	public final static String NAME = "XfscqyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XfscqyEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XfscqyEntity where nf=:nf and yf=:yf and dwid=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public XfscqyEntity getByDate(Date d, Company company, int hy) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XfscqyEntity where nf=:nf and yf=:yf and dwid=:compId and hyid=:hy");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("hy", hy);
		List<XfscqyEntity> tbs = q.getResultList();
		if (!tbs.isEmpty()){
			return tbs.get(0);
		}
		return null;
	}

	@Override
	public List<XfscqyEntity> getSumByDate(Date d, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, hyid, sum(qye) " +
				"from XfscqyEntity where nf=:nf and yf=:yf and dwid in (" + Util.toBMString(comps)+ ") " +
				"group by nf, yf, hyid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<XfscqyEntity> rets = new ArrayList<XfscqyEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			XfscqyEntity entity = new XfscqyEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setHyid((Integer) row[2]);
			entity.setQye((Double) row[3]);
			rets.add(entity);
		}
		return rets;
	}
	
	
}
