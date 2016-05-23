package com.tbea.ic.operation.model.dao.cpzlqk.byqjdacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.ByqJdAcptjjgEntity;



@Repository(ByqJdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqJdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<ByqJdAcptjjgEntity> implements ByqJdAcptjjgDao {
	public final static String NAME = "ByqJdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ByqJdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from ByqJdAcptjjgEntity");
		return q.getResultList();
	}
}
