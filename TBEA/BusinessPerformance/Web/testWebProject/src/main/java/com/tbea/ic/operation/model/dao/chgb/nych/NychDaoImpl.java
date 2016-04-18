package com.tbea.ic.operation.model.dao.chgb.nych;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.NychEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NychDaoImpl.NAME)
@Transactional("transactionManager")
public class NychDaoImpl extends AbstractReadWriteDaoImpl<NychEntity> implements NychDao {
	public final static String NAME = "NychDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	

	@Override
	public List<NychEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from NychEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"yf <= 12 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
	
	@Override
	public NychEntity getQCJYByDate(Date dSelected, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dSelected);
		
		Query q = this.getEntityManager().createQuery("from NychEntity where nf=:nf and yf=:yf and dwxx.id=:compId)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", 13); //default value for 年度期初结余
		q.setParameter("compId", company.getId());
		
		List<NychEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
