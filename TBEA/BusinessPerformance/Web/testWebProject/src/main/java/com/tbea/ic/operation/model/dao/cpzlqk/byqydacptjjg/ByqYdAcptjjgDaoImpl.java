package com.tbea.ic.operation.model.dao.cpzlqk.byqydacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.ByqYdAcptjjgEntity;



@Repository(ByqYdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqYdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<ByqYdAcptjjgEntity> implements ByqYdAcptjjgDao {
	public final static String NAME = "ByqYdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ByqYdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from ByqYdAcptjjgEntity");
		return q.getResultList();
	}
}
