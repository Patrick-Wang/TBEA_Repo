package com.tbea.ic.operation.model.dao.yszkgb.yszkzm;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkzmDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkzmDaoImpl extends AbstractReadWriteDaoImpl<YszkzmEntity> implements YszkzmDao {
	public final static String NAME = "YszkzmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public YszkzmEntity getByDate(Date d, Company company) {
		Query q = this.getEntityManager().createQuery("from YszkzmEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YszkzmEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
	
	@Override
	public YszkzmEntity getSumByDate(Date d, List<Company> comps) {
	
		Query q = this.getEntityManager().createQuery("select sum(zmje) as zmje, sum(hzzb) as hzzb, sum(yz) as yz from YszkzmEntity where nf=:nf and yf=:yf and dwxx.id in (" + Util.toBMString(comps) + ")");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<Object[]> ret = q.getResultList();
		YszkzmEntity entity = new YszkzmEntity();
		if (null != ret.get(0)[0]){
			entity.setZmje(((Double)ret.get(0)[0]));
		}
		if (null != ret.get(0)[1]){
			entity.setHzzb(((Double)ret.get(0)[1]));
		}
		if (null != ret.get(0)[2]){
			entity.setYz(((Double)ret.get(0)[2]));
		}
		
		return entity;
	}
}
