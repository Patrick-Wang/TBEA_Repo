package com.tbea.datatransfer.model.dao.zjxl.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.MRHKXL;

@Transactional("transactionManagerxl")
public class MRHKXLDaoImpl extends AbstractReadOnlyDaoImpl<MRHKXL> implements
		MRHKXLDao {

	@Override
	@PersistenceContext(unitName = "xlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKXL> getAllMRHK() {
		String sql = "From MRHKXL";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKXL> resultList = query.getResultList();
		return resultList;
	}

}
