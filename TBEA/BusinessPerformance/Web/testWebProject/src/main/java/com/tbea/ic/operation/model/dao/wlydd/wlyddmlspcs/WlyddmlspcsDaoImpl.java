package com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;
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
	
	@Override
	public WlyddmlspcsEntity getByDate(Date d, Company company, WlyddType type, Integer cpId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from WlyddmlspcsEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and cpid=:cpId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<WlyddmlspcsEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
		
	}

	@Override
	public List<WlyddmlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies, WlyddType type, Integer cpId) {
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, cpmc.id,cpmc.name," +
				"sum(cb)," +
				"sum(sr) " +
				"from WlyddmlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"tjfs=:tjfsId and " +
				"cpid=:cpId and " +
				"dwxx.id in (" + Util.toBMString(subCompanies) +") " +
				"group by nf, yf, cpmc.id, cpmc.name");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<WlyddmlspcsEntity> rets = new ArrayList<WlyddmlspcsEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			WlyddmlspcsEntity entity = new WlyddmlspcsEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			CpmcEntity cpmc = new CpmcEntity();
			cpmc.setId((Integer) row[2]);
			cpmc.setName((String) row[3]);
			entity.setCpmc(cpmc);
			entity.setCb((Double) row[4]);
			entity.setSr((Double) row[5]);
			entity.setTjfs(type.value());
			rets.add(entity);
		}
		return rets;
	}

	@Override
	public List<WlyddmlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, WlyddType byq, WlyddType xl, Integer cpId) {
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, cpmc.id,cpmc.name," +
				"sum(cb)," +
				"sum(sr) " +
				"from WlyddmlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"(tjfs=:xlId or tjfs=:byqId) and " +
				"cpid=:cpId and " +
				"dwxx.id in (" + Util.toBMString(comps) +") " +
				"group by nf, yf, cpmc.id, cpmc.name");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("xlId", xl.value());
		q.setParameter("byqId", byq.value());
		q.setParameter("cpId", cpId);
		List<WlyddmlspcsEntity> rets = new ArrayList<WlyddmlspcsEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			WlyddmlspcsEntity entity = new WlyddmlspcsEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			CpmcEntity cpmc = new CpmcEntity();
			cpmc.setId((Integer) row[2]);
			cpmc.setName((String) row[3]);
			entity.setCpmc(cpmc);
			entity.setCb((Double) row[4]);
			entity.setSr((Double) row[5]);
			rets.add(entity);
		}
		return rets;
	}
}
