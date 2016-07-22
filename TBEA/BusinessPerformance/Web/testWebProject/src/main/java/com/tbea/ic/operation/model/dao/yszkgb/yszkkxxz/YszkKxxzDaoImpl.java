package com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkKxxzDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkKxxzDaoImpl extends AbstractReadWriteDaoImpl<YszkKxxzEntity> implements YszkKxxzDao {
	public final static String NAME = "YszkKxxzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YszkKxxzEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from YszkKxxzEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YszkKxxzEntity getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from YszkKxxzEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YszkKxxzEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<YszkKxxzEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
						"sum(yq0z1y),"+
						"sum(yq1z3y),"+
						"sum(yq3z6y),"+
						"sum(yq6z12y),"+
						"sum(yq1nys),"+
						"sum(wdq),"+
						"sum(wdqzbj)"+
						"from YszkKxxzEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<YszkKxxzEntity> rets = new ArrayList<YszkKxxzEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			YszkKxxzEntity entity = new YszkKxxzEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setYq0z1y((Double) row[2]);
			entity.setYq1z3y((Double) row[3]);
			entity.setYq3z6y((Double) row[4]);
			entity.setYq6z12y((Double) row[5]);
			entity.setYq1nys((Double) row[6]);
			entity.setWdq((Double) row[7]);
			entity.setWdqzbj((Double) row[8]);
			rets.add(entity);
		}
		return rets;
	}
}
