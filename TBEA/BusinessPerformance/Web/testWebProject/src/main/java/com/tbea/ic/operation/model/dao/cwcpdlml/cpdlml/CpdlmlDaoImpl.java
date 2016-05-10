package com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;



@Repository(CpdlmlDaoImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlDaoImpl extends AbstractReadWriteDaoImpl<CpdlmlEntity> implements CpdlmlDao {
	public final static String NAME = "CpdlmlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public CpdlmlEntity getByDw(Date d, Company company, Integer cpid) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from CpdlmlEntity where nf=:nf and yf=:yf and dwid=:compId and cpdl = :cpid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("cpid", cpid);
		List<CpdlmlEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
