package com.tbea.ic.operation.model.dao.cpzlqk.byqadwtjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.ByqAdwtjjgEntity;



@Repository(ByqAdwtjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqAdwtjjgDaoImpl extends AbstractReadWriteDaoImpl<ByqAdwtjjgEntity> implements ByqAdwtjjgDao {
	public final static String NAME = "ByqAdwtjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ByqAdwtjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from ByqAdwtjjgEntity");
		return q.getResultList();
	}
}
