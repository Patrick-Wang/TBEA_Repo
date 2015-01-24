package com.tbea.datatransfer.model.dao.zjsb.ydsjhkqk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.YDSJHKQKBYQ;

@Transactional("transactionManagertb2")
public class YDSJHKQKSBDaoImpl extends AbstractReadOnlyDaoImpl<YDSJHKQKBYQ>
		implements YDSJHKQKSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDSJHKQKBYQ> getAllYDSJHKQK() {
		String sql = "From YDSJHKQKBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<YDSJHKQKBYQ> resultList = query.getResultList();
		return resultList;
	}

}
