package com.tbea.ic.operation.model.dao.cpzlqk.xkjdacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.XkJdAcptjjgEntity;



@Repository(XkJdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class XkJdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<XkJdAcptjjgEntity> implements XkJdAcptjjgDao {
	public final static String NAME = "XkJdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<XkJdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from XkJdAcptjjgEntity");
		return q.getResultList();
	}
}
