package com.tbea.datatransfer.model.dao.inner.ztyszkfxb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.ZTYSZKFXB;

@Transactional("transactionManager")
public class ZTYSZKFXBDaoImpl extends AbstractReadWriteDaoImpl<ZTYSZKFXB> implements
		ZTYSZKFXBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateZTYSZKFXB() {
		String sql = "truncate table ZTYSZKFXB";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}