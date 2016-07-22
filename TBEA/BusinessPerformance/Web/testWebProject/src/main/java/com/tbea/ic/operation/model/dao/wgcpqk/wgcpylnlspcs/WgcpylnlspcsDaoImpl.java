package com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs;


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
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;



@Repository(WgcpylnlspcsDaoImpl.NAME)
@Transactional("transactionManager")
public class WgcpylnlspcsDaoImpl extends AbstractReadWriteDaoImpl<WgcpylnlspcsEntity> implements WgcpylnlspcsDao {
	public final static String NAME = "WgcpylnlspcsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<WgcpylnlspcsEntity> getByDate(Date ds, Date de, Company company, WgcpqkType type) {
		Query q = this.getEntityManager().createQuery("from WgcpylnlspcsEntity where " + 
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
	public List<WgcpylnlspcsEntity> getByDate(Date d, Company company, WgcpqkType type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from WgcpylnlspcsEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		List<WgcpylnlspcsEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list;
	}
	
	@Override
	public List<WgcpylnlspcsEntity> getByDate(Date ds, Date de, Company company, WgcpqkType type, Integer cpId) {
		Query q = this.getEntityManager().createQuery("from WgcpylnlspcsEntity where " + 
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
	public WgcpylnlspcsEntity getByDate(Date d, Company company, WgcpqkType type, Integer cpId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from WgcpylnlspcsEntity where nf=:nf and yf=:yf and tjfs=:tjfsId and cpid=:cpId and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<WgcpylnlspcsEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
		
	}

	@Override
	public List<WgcpylnlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, WgcpqkType ylfxWgcpylnlByqZh,
			WgcpqkType ylfxWgcpylnlXlZh, Integer cpId) {
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, cpmc.id,cpmc.name," +
				"sum(cb)," +
				"sum(sr) " +
				"from WgcpylnlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"(tjfs=:xlId or tjfs=:byqId) and " +
				"cpid=:cpId and " +
				"dwxx.id in (" + Util.toBMString(comps) +") " +
				"group by nf, yf, cpmc.id, cpmc.name");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("xlId", ylfxWgcpylnlXlZh.value());
		q.setParameter("byqId", ylfxWgcpylnlByqZh.value());
		q.setParameter("cpId", cpId);
		List<WgcpylnlspcsEntity> rets = new ArrayList<WgcpylnlspcsEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			WgcpylnlspcsEntity entity = new WgcpylnlspcsEntity();
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

	@Override
	public List<WgcpylnlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, WgcpqkType type, Integer cpId) {
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, cpmc.id,cpmc.name," +
				"sum(cb)," +
				"sum(sr) " +
				"from WgcpylnlspcsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"tjfs=:tjfsId and " +
				"cpid=:cpId and " +
				"dwxx.id in (" + Util.toBMString(comps) +") " +
				"group by nf, yf, cpmc.id, cpmc.name");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("tjfsId", type.value());
		q.setParameter("cpId", cpId);
		List<WgcpylnlspcsEntity> rets = new ArrayList<WgcpylnlspcsEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			WgcpylnlspcsEntity entity = new WgcpylnlspcsEntity();
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
}
