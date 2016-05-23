package com.tbea.ic.operation.model.dao.cpzlqk.byqbhglb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqBhglbEntity;



@Repository(ByqBhglbDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqBhglbDaoImpl extends AbstractReadWriteDaoImpl<ByqBhglbEntity> implements ByqBhglbDao {
	public final static String NAME = "ByqBhglbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ByqBhglbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from ByqBhglbEntity");
		return q.getResultList();
	}
}
