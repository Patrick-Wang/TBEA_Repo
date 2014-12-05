package com.tbea.datatransfer.model.dao.local.xjlrb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.XJLRB;

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