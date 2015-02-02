package com.tbea.datatransfer.model.dao.zjbyq.byqzx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.BYQZX;

@Transactional("transactionManagerxb")
public class BYQZXXBDaoImpl extends AbstractReadOnlyDaoImpl<BYQZX> implements
		BYQZXDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BYQZX> getAllBYQZX() {
		String sql = "From BYQZX";
		Query query = getEntityManager().createQuery(sql);
		List<BYQZX> resultList = query.getResultList();
		return resultList;
	}

}
