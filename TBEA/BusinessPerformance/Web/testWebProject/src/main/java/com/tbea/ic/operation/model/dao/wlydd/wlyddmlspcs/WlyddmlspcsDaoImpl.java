package com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(WlyddmlspcsDaoImpl.NAME)
@Transactional("transactionManager")
public class WlyddmlspcsDaoImpl extends AbstractReadWriteDaoImpl<WlyddmlspcsEntity> implements WlyddmlspcsDao {
	public final static String NAME = "WlyddmlspcsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<WlyddmlspcsEntity> getByDate(Date ds, Date de, Company company, WlyddType type) {
		Query q = this.getEntityManager().createQuery("from WlyddmlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"tjfs=:tjfsId and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		return q.getResultList();
	}
	
	@Override
	public List<WlyddmlspcsEntity> getByDate(Date d, Company company, WlyddType type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from WlyddmlspcsEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		List<WlyddmlspcsEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list;
	}
	
	@Override
	public List<WlyddmlspcsEntity> getByDate(Date ds, Date de, Company company, WlyddType type, Integer cpId) {
		Query q = this.getEntityManager().createQuery("from WlyddmlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"tjfs=:tjfsId and " +
				"cpid=:cpId and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		return q.getResultList();
	}
	
}
