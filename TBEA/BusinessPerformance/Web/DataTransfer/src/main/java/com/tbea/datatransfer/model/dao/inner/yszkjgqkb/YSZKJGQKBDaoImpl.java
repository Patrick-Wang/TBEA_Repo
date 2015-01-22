package com.tbea.datatransfer.model.dao.inner.yszkjgqkb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.YSZKJGQKB;

@Transactional("transactionManager")
public class YSZKJGQKBDaoImpl extends AbstractReadWriteDaoImpl<YSZKJGQKB> implements
		YSZKJGQKBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateYSZKJGQKB() {
		String sql = "truncate table YSZKJGQKB";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}