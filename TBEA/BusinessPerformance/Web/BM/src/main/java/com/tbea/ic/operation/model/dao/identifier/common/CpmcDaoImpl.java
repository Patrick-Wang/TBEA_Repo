package com.tbea.ic.operation.model.dao.identifier.common;


import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CpmcDaoImpl.NAME)
@Transactional("transactionManager")
public class CpmcDaoImpl extends AbstractReadWriteDaoImpl<CpmcEntity> implements CpmcDao {
	public final static String NAME = "CpmcDaoImpl";

	EntityManager manager;
	
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public CpmcEntity getById(Integer id) {
		Query q = this.getEntityManager().createQuery("from CpmcEntity where id=:cpId");
		q.setParameter("cpId", id);
		List<CpmcEntity> result = q.getResultList();
		if (result == null) {
			return null;
		}
		return result.get(0);
	}
}
