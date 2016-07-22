package com.tbea.ic.operation.model.dao.chgb.nych;


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
import com.tbea.ic.operation.model.entity.chgb.NychEntity;



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


	@Override
	public List<NychEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
						"sum(ycl),"+	
						"sum(yl),"+
						"sum(bpbj),"+
						"sum(kcsp),"+
						"sum(sccbDpbtf),"+
						"sum(fcsp),"+
						"sum(dh),"+
						"sum(hj)"+
						"from NychEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<NychEntity> rets = new ArrayList<NychEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			NychEntity entity = new NychEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setYcl((Double) row[2]);
			entity.setYl((Double) row[3]);
			entity.setBpbj((Double) row[4]);
			entity.setKcsp((Double) row[5]);
			entity.setSccbDpbtf((Double) row[6]);
			entity.setFcsp((Double) row[7]);
			entity.setDh((Double) row[8]);
			entity.setHj((Double) row[9]);
			rets.add(entity);
		}
		return rets;
	}


	@Override
	public NychEntity getSumQCJYByDate(Date date,
			List<Company> subCompanies) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		Query q = this.getEntityManager().createQuery(
					"select nf, yf," +
							"sum(ycl),"+	
							"sum(yl),"+
							"sum(bpbj),"+
							"sum(kcsp),"+
							"sum(sccbDpbtf),"+
							"sum(fcsp),"+
							"sum(dh),"+
							"sum(hj)"+
					"from NychEntity where nf=:nf and yf=:yf and "+
					"dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", 13); //default value for 年度期初结余
		
		List<NychEntity> rets = new ArrayList<NychEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			NychEntity entity = new NychEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setYcl((Double) row[2]);
			entity.setYl((Double) row[3]);
			entity.setBpbj((Double) row[4]);
			entity.setKcsp((Double) row[5]);
			entity.setSccbDpbtf((Double) row[6]);
			entity.setFcsp((Double) row[7]);
			entity.setDh((Double) row[8]);
			entity.setHj((Double) row[9]);
			rets.add(entity);
		}
		if (rets.isEmpty()){
			return null;
		}
		return rets.get(0);
	}
}
