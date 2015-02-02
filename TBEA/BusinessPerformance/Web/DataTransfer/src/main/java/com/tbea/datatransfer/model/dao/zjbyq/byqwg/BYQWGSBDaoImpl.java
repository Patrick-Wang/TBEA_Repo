package com.tbea.datatransfer.model.dao.zjbyq.byqwg;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.BYQWG;

@Transactional("transactionManagersb")
public class BYQWGSBDaoImpl extends AbstractReadOnlyDaoImpl<BYQWG> implements
		BYQWGDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BYQWG> getAllBYQWG() {
		String sql = "From BYQWG";
		Query query = getEntityManager().createQuery(sql);
		List<BYQWG> resultList = query.getResultList();
		return resultList;
	}

}
