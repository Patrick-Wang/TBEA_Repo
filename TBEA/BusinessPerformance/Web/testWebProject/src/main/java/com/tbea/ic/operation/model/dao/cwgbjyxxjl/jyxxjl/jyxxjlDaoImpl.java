package com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;



@Repository(jyxxjlDaoImpl.NAME)
@Transactional("transactionManager")
public class jyxxjlDaoImpl extends AbstractReadWriteDaoImpl<JyxxjlEntity> implements jyxxjlDao {
	public final static String NAME = "jyxxjlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<JyxxjlEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from JyxxjlEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public List<JyxxjlEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from JyxxjlEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<JyxxjlEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list;
	}
	
	@Override
	public List<JyxxjlEntity> getByDate(Date ds, Date de, Company company, Integer kmId) {
		Query q = this.getEntityManager().createQuery("from JyxxjlEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"km.id=:kmId and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		q.setParameter("kmId", kmId);
		return q.getResultList();
	}

	@Override
	public JyxxjlEntity getByDate(Date d, Company company, Integer kmId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from JyxxjlEntity where nf=:nf and yf=:yf and km.id=:kmId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("kmId", kmId);
		List<JyxxjlEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
}
