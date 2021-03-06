package com.tbea.ic.operation.model.dao.yszkgb.yszkzl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
