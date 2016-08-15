package com.tbea.ic.operation.model.dao.cpzlqk.pdjdacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.PdJdAcptjjgEntity;



@Repository(PdJdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class PdJdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<PdJdAcptjjgEntity> implements PdJdAcptjjgDao {
	public final static String NAME = "PdJdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<PdJdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from PdJdAcptjjgEntity");
		return q.getResultList();
	}
}
