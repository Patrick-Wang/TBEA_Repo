package com.tbea.datatransfer.model.dao.zjsb.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKBYQ;

@Transactional("transactionManagersb")
public class MRHKSBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKBYQ> implements
		MRHKSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
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
