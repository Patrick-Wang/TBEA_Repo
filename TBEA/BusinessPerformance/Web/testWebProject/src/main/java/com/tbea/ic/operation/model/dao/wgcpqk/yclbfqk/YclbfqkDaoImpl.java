package com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk;


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
import com.tbea.ic.operation.model.entity.wgcpqk.YclbfqkEntity;



@Repository(YclbfqkDaoImpl.NAME)
@Transactional("transactionManager")
public class YclbfqkDaoImpl extends AbstractReadWriteDaoImpl<YclbfqkEntity> implements YclbfqkDao {
	public final static String NAME = "YclbfqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YclbfqkEntity> getByDate(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YclbfqkEntity where  nf=:nf and yf=:yf and dwid=:cpId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("cpId", company.getId());
		return q.getResultList();
	}

	@Override
	public YclbfqkEntity getByDate(Date d, Company company, Integer clid) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from YclbfqkEntity where nf=:nf and yf=:yf and dwid = :compId and clid = :clid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("clid", clid);
		List<YclbfqkEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<YclbfqkEntity> getSumByDate(Date date,
			List<Company> subCompanies) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery(
				"select nf, yf, clid, sum(lyl), sum(fl) " + 
				"from YclbfqkEntity where  nf=:nf and yf=:yf and dwid in (" + Util.toBMString(subCompanies)+ ") " + 
				"group by nf, yf, clid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<YclbfqkEntity> rets = new ArrayList<YclbfqkEntity>();
		List<Object[]> ret = q.getResultList();
		for (Object[] row : ret){
			YclbfqkEntity entity = new YclbfqkEntity();
			entity.setNf((Integer) row[0]);
			entity.setYf((Integer) row[1]);
			entity.setClid((Integer) row[2]);
			entity.setLyl((Double) row[3]);
			entity.setFl((Double) row[4]);
			rets.add(entity);
		}
		return rets;
	}
}
