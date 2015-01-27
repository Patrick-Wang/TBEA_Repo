package com.tbea.datatransfer.model.dao.zjbyq.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKBYQ;

@Transactional("transactionManagertb")
public class MRHKTBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKBYQ> implements
		MRHKBYQDao {

	@Override
	@PersistenceContext(unitName = "tbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKBYQ> getAllMRHK() {
		String sql = "From MRHKBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKBYQ> resultList = query.getResultList();
		return resultList;
	}

}
