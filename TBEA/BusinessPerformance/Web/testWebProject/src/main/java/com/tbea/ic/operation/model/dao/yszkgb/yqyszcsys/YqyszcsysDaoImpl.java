package com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YqyszcsysDaoImpl.NAME)
@Transactional("transactionManager")
public class YqyszcsysDaoImpl extends AbstractReadWriteDaoImpl<YqyszcsysEntity> implements YqyszcsysDao {
	public final static String NAME = "YqyszcsysDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YqyszcsysEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from YqyszcsysEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YqyszcsysEntity getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from YqyszcsysEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YqyszcsysEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<YqyszcsysEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
								"sum(nbys),"+
						"sum(khzx),"+
						"sum(gdfk),"+
						"sum(xmbh),"+
						"sum(htys),"+
						"sum(sxbl),"+
						"sum(ss)"+
						"from YqyszcsysEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<YqyszcsysEntity> rets = new ArrayList<YqyszcsysEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			YqyszcsysEntity entity = new YqyszcsysEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setKhzx((Double) row[2]);
			entity.setGdfk((Double) row[3]);
			entity.setXmbh((Double) row[4]);
			entity.setHtys((Double) row[5]);
			entity.setSxbl((Double) row[6]);
			entity.setSs((Double) row[7]);
			rets.add(entity);
		}
		return rets;
	}
}
