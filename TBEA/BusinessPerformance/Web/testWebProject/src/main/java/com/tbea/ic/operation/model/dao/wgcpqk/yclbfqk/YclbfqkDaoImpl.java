package com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.wgcpqk.YclbfqkEntity;
import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.YclbfqkDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



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
		Query q = this.getEntityManager().createQuery("from YclbfqkEntity where compId=:cpId");
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}

	@Override
	public YclbfqkEntity getByDate(Date d, Company company, Integer clid) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from YclbfqkEntity where nf=:nf and yf=:yf and dwid = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<YclbfqkEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
