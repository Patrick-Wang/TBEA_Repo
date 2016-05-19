package com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;



@Repository(SzDaoImpl.NAME)
@Transactional("transactionManager")
public class SzDaoImpl extends AbstractReadWriteDaoImpl<SzEntity> implements SzDao {
	public final static String NAME = "SzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<SzEntity> getAll() {
		Query q = this.getEntityManager().createQuery("from SzEntity");
		return q.getResultList();
	}

	@Override
	public int getSzCount() {
		Query q = this.getEntityManager().createQuery("select count(*) from SzEntity");
		return ((Long)q.getResultList().get(0)).intValue();
	}
}
