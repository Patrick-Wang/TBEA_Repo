package com.tbea.datatransfer.model.dao.inner.blht;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.BLHT;

@Transactional("transactionManager")
public class BLHTDaoImpl extends AbstractReadWriteDaoImpl<BLHT> implements BLHTDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateBLHT() {
		String sql = "truncate table blhtdqqkhzb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}
