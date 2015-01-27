package com.tbea.datatransfer.model.dao.zjxl.ydsjhkqk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YDSJHKQKXL;

@Transactional("transactionManagerll")
public class YDSJHKQKLLDaoImpl extends AbstractReadOnlyDaoImpl<YDSJHKQKXL>
		implements YDSJHKQKXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDSJHKQKXL> getAllYDSJHKQK() {
		String sql = "From YDSJHKQKXL";
		Query query = getEntityManager().createQuery(sql);
		List<YDSJHKQKXL> resultList = query.getResultList();
		return resultList;
	}

}
