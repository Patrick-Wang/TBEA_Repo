package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;


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
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;



@Repository(XfcpqyDaoImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyDaoImpl extends AbstractReadWriteDaoImpl<XfcpqyEntity> implements XfcpqyDao {
	public final static String NAME = "XfcpqyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public 	List<XfcpqyEntity> getByDate(Date ds, Date de, Company company, SbdscqyqkType type) {
		Query q = this.getEntityManager().createQuery("from XfcpqyEntity where " + 
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
	public 	List<XfcpqyEntity> getByDate(Date d, Company company, SbdscqyqkType type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XfcpqyEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		List<XfcpqyEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list;
	}
	
	@Override
	public 	List<XfcpqyEntity> getByDate(Date ds, Date de, Company company, SbdscqyqkType type, Integer cpId) {
		Query q = this.getEntityManager().createQuery("from XfcpqyEntity where " + 
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

	@Override
	public 	XfcpqyEntity getByDate(Date d, Company company, SbdscqyqkType type, Integer cpId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XfcpqyEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and cpid=:cpId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<XfcpqyEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
