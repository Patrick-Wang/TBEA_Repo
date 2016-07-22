package com.tbea.ic.operation.model.dao.chgb.chxzqk;


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
import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;



@Repository(ChxzqkDaoImpl.NAME)
@Transactional("transactionManager")
public class ChxzqkDaoImpl extends AbstractReadWriteDaoImpl<ChxzqkEntity> implements ChxzqkDao {
	public final static String NAME = "ChxzqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ChxzqkEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from ChxzqkEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
	
	@Override
	public ChxzqkEntity getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from ChxzqkEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<ChxzqkEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<ChxzqkEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
						"sum(ycl),"+
						"sum(bcp),"+
						"sum(sjkcsp),"+
						"sum(yfhwkp),"+
						"sum(qhfdyk),"+
						"sum(qhpcyk),"+
						"sum(wfhykp),"+
						"sum(qt)"+
						"from ChxzqkEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<ChxzqkEntity> rets = new ArrayList<ChxzqkEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			ChxzqkEntity entity = new ChxzqkEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setYcl((Double) row[2]);
			entity.setBcp((Double) row[3]);
			entity.setSjkcsp((Double) row[4]);
			entity.setYfhwkp((Double) row[5]);
			entity.setQhfdyk((Double) row[6]);
			entity.setQhpcyk((Double) row[7]);
			entity.setWfhykp((Double) row[8]);
			entity.setQt((Double) row[9]);
			rets.add(entity);
		}
		return rets;
	}
}
