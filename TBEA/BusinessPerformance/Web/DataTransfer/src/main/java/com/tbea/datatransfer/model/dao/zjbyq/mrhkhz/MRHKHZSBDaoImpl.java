package com.tbea.datatransfer.model.dao.zjbyq.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKHZBYQ;

@Transactional("transactionManagersb")
public class MRHKHZSBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKHZBYQ>
		implements MRHKHZBYQDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZBYQ> getAllMRHKHZ() {
		String sql = "From MRHKHZBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZBYQ> resultList = query.getResultList();
		return resultList;
	}

}
