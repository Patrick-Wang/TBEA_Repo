package com.tbea.datatransfer.model.dao.inner.ydzbfdw;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.YDZBFDW;

@Transactional("transactionManager")
public class YDZBFDWDaoImpl extends AbstractReadWriteDaoImpl<YDZBFDW> implements
		YDZBFDWDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateYDZBFDW() {
		String sql = "truncate table YDZBFDW";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}