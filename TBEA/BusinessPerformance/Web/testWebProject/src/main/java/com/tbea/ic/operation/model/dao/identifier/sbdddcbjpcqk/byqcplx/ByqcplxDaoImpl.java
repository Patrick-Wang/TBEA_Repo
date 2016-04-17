package com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx;


import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.sbdddcbjpcqk.ByqcplxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx.ByqcplxDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ByqcplxDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqcplxDaoImpl extends AbstractReadWriteDaoImpl<ByqcplxEntity> implements ByqcplxDao {
	public final static String NAME = "ByqcplxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ByqcplxEntity> getAll() {
		Query q = this.getEntityManager().createQuery("from ByqcplxEntity");
		return q.getResultList();
	}
}
