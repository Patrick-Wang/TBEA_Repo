package com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx;


import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.sbdddcbjpcqk.XlcplxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx.XlcplxDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(XlcplxDaoImpl.NAME)
@Transactional("transactionManager")
public class XlcplxDaoImpl extends AbstractReadWriteDaoImpl<XlcplxEntity> implements XlcplxDao {
	public final static String NAME = "XlcplxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XlcplxEntity> getAll() {
		Query q = this.getEntityManager().createQuery("from XlcplxEntity");
		return q.getResultList();
	}
}
