package com.tbea.datatransfer.model.dao.inner.yqkqsbhb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.YQKQSBHB;

@Transactional("transactionManager")
public class YQKQSBHBDaoImpl extends AbstractReadWriteDaoImpl<YQKQSBHB> implements
		YQKQSBHBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateYQKQSBHB() {
		String sql = "truncate table YQKQSBHB";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}