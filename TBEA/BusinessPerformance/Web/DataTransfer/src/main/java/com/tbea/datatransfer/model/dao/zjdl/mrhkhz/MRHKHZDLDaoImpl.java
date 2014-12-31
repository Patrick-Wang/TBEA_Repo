package com.tbea.datatransfer.model.dao.zjdl.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.MRHKHZDL;

@Transactional("transactionManagerdl")
public class MRHKHZDLDaoImpl extends AbstractReadOnlyDaoImpl<MRHKHZDL>
		implements MRHKHZDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZDL> getAllMRHKHZDL() {
		String sql = "From MRHKHZDL";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZDL> resultList = query.getResultList();
		return resultList;
	}

}
