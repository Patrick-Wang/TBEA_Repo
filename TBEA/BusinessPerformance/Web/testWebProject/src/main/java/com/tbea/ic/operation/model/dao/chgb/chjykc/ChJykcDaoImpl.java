package com.tbea.ic.operation.model.dao.chgb.chjykc;


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
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;



@Repository(ChJykcDaoImpl.NAME)
@Transactional("transactionManager")
public class ChJykcDaoImpl extends AbstractReadWriteDaoImpl<ChJykcEntity> implements ChJykcDao {
	public final static String NAME = "ChJykcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ChJykcEntity> getByDate(Date d, Company company) {
		Query q = this.getEntityManager().createQuery("from ChJykcEntity where nf=:nf and yf=:yf and dwxx.id=:compId");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public List<ChJykcEntity> getSumByDate(Date d, List<Company> subCompanies) {
		Query q = this
				.getEntityManager()
				.createQuery(
						"select nf, yf, jykcxmEntity.id, jykcxmEntity.name, " +
						"sum(syye),"+
						"sum(byxz),"+
						"sum(bycz),"+
						"sum(qmye) "+
						"from ChJykcEntity where "
								+ " nf=:nf and yf=:yf and "
								+ " dwxx.id in (" + Util.toBMString(subCompanies)+ ") group by nf, yf, jykcxmEntity.id, jykcxmEntity.name");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<ChJykcEntity> rets = new ArrayList<ChJykcEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			ChJykcEntity entity = new ChJykcEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setSyye((Double) row[4]);
			entity.setByxz((Double) row[5]);
			entity.setBycz((Double) row[6]);
			entity.setQmye((Double) row[7]);
			JykcxmEntity jykc = new JykcxmEntity();
			jykc.setId((Integer) row[2]);
			jykc.setName((String) row[3]);
			entity.setJykcxmEntity(jykc);
			rets.add(entity);
		}
		return rets;
	}
}
