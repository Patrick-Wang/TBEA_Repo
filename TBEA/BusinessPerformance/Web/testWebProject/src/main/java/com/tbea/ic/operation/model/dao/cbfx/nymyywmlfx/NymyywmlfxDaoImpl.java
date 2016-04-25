package com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cbfx.NymyywmlfxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NymyywmlfxDaoImpl.NAME)
@Transactional("transactionManager")
public class NymyywmlfxDaoImpl extends AbstractReadWriteDaoImpl<NymyywmlfxEntity> implements NymyywmlfxDao {
	public final static String NAME = "NymyywmlfxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<NymyywmlfxEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from NymyywmlfxEntity where nf=:nf and yf=:yf and dwid=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		return q.getResultList();
	}
}
