package com.tbea.datatransfer.model.dao.inner.cqk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.CQK;

@Repository
@Transactional("transactionManager")
public class CQKDaoImpl extends AbstractReadWriteDaoImpl<CQK> implements CQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateCQK() {
		String sql = "truncate table cqk";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}
