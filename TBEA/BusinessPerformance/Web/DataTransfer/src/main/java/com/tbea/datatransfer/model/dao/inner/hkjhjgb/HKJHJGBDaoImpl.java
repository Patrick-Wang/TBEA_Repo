package com.tbea.datatransfer.model.dao.inner.hkjhjgb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.HKJHJGB;

@Repository
@Transactional("transactionManager")
public class HKJHJGBDaoImpl extends AbstractReadWriteDaoImpl<HKJHJGB> implements
		HKJHJGBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void deleteHKJHJGBByNy(String ny) {
		String sql = "Delete From HKJHJGB Where ny = :ny";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("ny", ny);
		query.executeUpdate();
	}

	@Override
	public void truncateHKJHJGB() {
		String sql = "truncate table hkjhjgb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}
