package com.tbea.ic.operation.model.dao.cpzlqk.xlacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.XlAcptjjgEntity;



@Repository(XlAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class XlAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<XlAcptjjgEntity> implements XlAcptjjgDao {
	public final static String NAME = "XlAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XlAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from XlAcptjjgEntity");
		return q.getResultList();
	}
}
