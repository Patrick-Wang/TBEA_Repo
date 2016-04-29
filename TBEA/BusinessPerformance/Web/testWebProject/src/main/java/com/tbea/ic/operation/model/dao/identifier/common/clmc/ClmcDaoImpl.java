package com.tbea.ic.operation.model.dao.identifier.common.clmc;


import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.identifier.common.ClmcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.common.clmc.ClmcDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ClmcDaoImpl.NAME)
@Transactional("transactionManager")
public class ClmcDaoImpl extends AbstractReadWriteDaoImpl<ClmcEntity> implements ClmcDao {
	public final static String NAME = "ClmcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public ClmcEntity getById(Integer id) {
		Query q = this.getEntityManager().createQuery("from ClmcEntity where id=:cpId");
		q.setParameter("cpId", id);
		List<ClmcEntity> result = q.getResultList();
		if (result == null) {
			return null;
		}
		return result.get(0);
	}
}
