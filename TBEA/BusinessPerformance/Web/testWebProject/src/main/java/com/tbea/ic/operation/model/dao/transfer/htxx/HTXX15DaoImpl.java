package com.tbea.ic.operation.model.dao.transfer.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.ic.operation.model.entity.yszk15.HTXX15;

@Transactional("transactionManager2")
public class HTXX15DaoImpl extends AbstractReadOnlyDaoImpl<HTXX15> implements
		HTXX15Dao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXX15> getAllHTXX15() {
		String sql = "From HTXX15";
		Query query = getEntityManager().createQuery(sql);
		List<HTXX15> resultList = query.getResultList();
		return resultList;
	}

}
