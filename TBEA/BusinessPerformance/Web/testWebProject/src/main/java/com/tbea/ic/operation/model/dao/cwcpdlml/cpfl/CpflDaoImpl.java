package com.tbea.ic.operation.model.dao.cwcpdlml.cpfl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cwcpdlml.CpflEntity;



@Repository(CpflDaoImpl.NAME)
@Transactional("transactionManager")
public class CpflDaoImpl extends AbstractReadWriteDaoImpl<CpflEntity> implements CpflDao {
	public final static String NAME = "CpflDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<CpflEntity> getCpflByCy(Integer cyId) {
		Query q = this.getEntityManager().createQuery("from CpflEntity where cy.id=:cyid");
		q.setParameter("cyid", cyId);
		return q.getResultList();
	}
}
