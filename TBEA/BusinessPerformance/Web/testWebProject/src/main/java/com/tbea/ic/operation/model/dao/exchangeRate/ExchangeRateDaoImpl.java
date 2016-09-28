package com.tbea.ic.operation.model.dao.exchangeRate;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.model.entity.ExchangeRate;

@Repository
@Transactional("transactionManager")
public class ExchangeRateDaoImpl extends AbstractReadWriteDaoImpl<ExchangeRate> implements
ExchangeRateDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ExchangeRate> getAll() {
		Query q = this.getEntityManager().createQuery("from ExchangeRate");
		return q.getResultList();
	}

	@Override
	public ExchangeRate getByDate(Date d) {
		EasyCalendar ec = new EasyCalendar(d);
		Query q = this.getEntityManager().createQuery("from ExchangeRate where nf = :nf");
		q.setParameter("nf", ec.getYear());
		List<ExchangeRate> ers = q.getResultList();
		if (ers.isEmpty()){
			return null;
		}
		return ers.get(0);
	}

}
