package com.tbea.datatransfer.model.dao.inner.yqysysfx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.YQYSYSFX;

@Transactional("transactionManager")
public class YQYSYSFXDaoImpl extends AbstractReadWriteDaoImpl<YQYSYSFX> implements
		YQYSYSFXDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateYQYSYSFX() {
		String sql = "truncate table YQYSYSFX";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}