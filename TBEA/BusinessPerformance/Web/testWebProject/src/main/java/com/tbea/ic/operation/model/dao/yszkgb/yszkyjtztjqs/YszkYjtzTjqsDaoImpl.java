package com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkYjtzTjqsDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkYjtzTjqsDaoImpl extends AbstractReadWriteDaoImpl<YszkYjtzTjqsEntity> implements YszkYjtzTjqsDao {
	public final static String NAME = "YszkYjtzTjqsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YszkYjtzTjqsEntity> getByDate(Date ds, Date de, Company company) {
		Query q = this.getEntityManager().createQuery("from YszkYjtzTjqsEntity where " + 
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
				"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
				"dwxx.id=:compId)");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YszkYjtzTjqsEntity getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from YszkYjtzTjqsEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YszkYjtzTjqsEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<YszkYjtzTjqsEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf," +
						"sum(cwzmysjsye),"+
						"sum(blye),"+
						"sum(hfpwkje),"+
						"sum(pkhwfje),"+
						"sum(yskcjys),"+
						"sum(xyzcjys),"+
						"sum(qtyskmyx),"+
						"sum(yjtzyszkye)"+
						"from YszkYjtzTjqsEntity where "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and "
								+ "dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and "
								+ "dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf");
		q.setParameter("dStart", ds);
		q.setParameter("dEnd", de);
		List<YszkYjtzTjqsEntity> rets = new ArrayList<YszkYjtzTjqsEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			YszkYjtzTjqsEntity entity = new YszkYjtzTjqsEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setCwzmysjsye((Double) row[2]);
			entity.setBlye((Double) row[3]);
			entity.setHfpwkje((Double) row[4]);
			entity.setPkhwfje((Double) row[5]);
			entity.setYskcjys((Double) row[6]);
			entity.setXyzcjys((Double) row[7]);
			entity.setQtyskmyx((Double) row[8]);
			entity.setYjtzyszkye((Double) row[9]);
			rets.add(entity);
		}
		return rets;
	}
}
