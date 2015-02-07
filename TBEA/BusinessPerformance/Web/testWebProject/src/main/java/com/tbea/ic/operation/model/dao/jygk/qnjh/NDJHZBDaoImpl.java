package com.tbea.ic.operation.model.dao.jygk.qnjh;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class NDJHZBDaoImpl extends AbstractReadWriteDaoImpl<NDJHZB> implements NDJHZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public NDJHZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where zbxx.id = :id and nf = :nf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("comp", company.getId());
		List<NDJHZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<NDJHZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where nf = :nf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

}
