package com.tbea.datatransfer.model.dao.inner.xjlrb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.XJLRB;

@Transactional("transactionManager")
public class XJLRBDaoImpl extends AbstractReadWriteDaoImpl<XJLRB> implements
		XJLRBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateXJLRB() {
		String sql = "truncate table XJLRB";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}