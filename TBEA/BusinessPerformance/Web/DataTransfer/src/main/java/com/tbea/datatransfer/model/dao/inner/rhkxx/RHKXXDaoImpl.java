package com.tbea.datatransfer.model.dao.inner.rhkxx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.RHKXX;

@Repository
@Transactional("transactionManager")
public class RHKXXDaoImpl extends AbstractReadWriteDaoImpl<RHKXX> implements
		RHKXXDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateRHKXX() {
		String sql = "truncate table rhkxx";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}
