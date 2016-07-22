package com.tbea.ic.operation.model.dao.yszkgb.yszkzl;

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
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;

@Repository(YszkZlDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkZlDaoImpl extends AbstractReadWriteDaoImpl<YszkZlEntity>
		implements YszkZlDao {
	public final static String NAME = "YszkZlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YszkZlEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"from YszkZlEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YszkZlEntity getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this
				.getEntityManager()
				.createQuery(
						"from YszkZlEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YszkZlEntity> list = q.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);

	}

	@Override
	public List<YszkZlEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
						"sum(zl5nys)," +
						"sum(zl4z5n)," +
						"sum(zl3z4n)," +
						"sum(zl2z3n)," +
						"sum(zl1z2n)," +
						"sum(zl1nyn)," +
						"sum(hj)" +
						"from YszkZlEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<YszkZlEntity> rets = new ArrayList<YszkZlEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			YszkZlEntity entity = new YszkZlEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setZl5nys((Double) row[2]);
			entity.setZl4z5n((Double) row[3]);
			entity.setZl3z4n((Double) row[4]);
			entity.setZl2z3n((Double) row[5]);
			entity.setZl1nyn((Double) row[6]);
			entity.setHj((Double) row[7]);
			rets.add(entity);
		}
		return rets;
	}
}
