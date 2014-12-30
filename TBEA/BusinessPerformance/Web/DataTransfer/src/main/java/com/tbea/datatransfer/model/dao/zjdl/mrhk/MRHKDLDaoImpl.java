package com.tbea.datatransfer.model.dao.zjdl.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.MRHKDL;

@Transactional("transactionManagerdl")
public class MRHKDLDaoImpl extends AbstractReadOnlyDaoImpl<MRHKDL> implements
		MRHKDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKDL> getAllMRHKDL() {
		String sql = "From MRHKDL";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKDL> resultList = query.getResultList();
		return resultList;
	}

}
