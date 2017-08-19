package com.tbea.ic.operation.model.dao.cpzlqk.xkydacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.cpzlqk.XkYdAcptjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;



@Repository(XkYdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class XkYdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<XkYdAcptjjgEntity> implements XkYdAcptjjgDao {
	public final static String NAME = "XkYdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XkYdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from XkYdAcptjjgEntity");
		return q.getResultList();
	}
}
