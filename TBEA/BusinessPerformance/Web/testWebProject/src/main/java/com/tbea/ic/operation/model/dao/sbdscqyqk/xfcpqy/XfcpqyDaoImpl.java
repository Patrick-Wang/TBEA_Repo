package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;


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
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;



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

	@Override
	public List<XfcpqyEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, SbdscqyqkType type, Integer cpId) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf, cpmc.id, cpmc.name," +
						"sum(qye) " + 
						"from XfcpqyEntity where " + 
						"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
						"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
						"tjfs=:tjfsId and " +
						"cpmc.id=:cpId and " +
						"dwxx.id in (" + Util.toBMString(comps) +") " +
						"group by nf, yf, cpmc.id, cpmc.name");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<XfcpqyEntity> rets = new ArrayList<XfcpqyEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			XfcpqyEntity entity = new XfcpqyEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			CpmcEntity cpmc = new CpmcEntity();
			cpmc.setId((Integer) row[2]);
			cpmc.setName((String) row[3]);
			entity.setCpmc(cpmc);
			entity.setQye((Double) row[4]);
			entity.setTjfs(type.value());
			rets.add(entity);
		}
		return rets;
	}
}
