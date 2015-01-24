package com.tbea.datatransfer.model.dao.zjdl.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.MRHKHZXL;

@Transactional("transactionManagerdl")
public class MRHKHZDLDaoImpl extends AbstractReadOnlyDaoImpl<MRHKHZXL>
		implements MRHKHZDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZXL> getAllMRHKHZ() {
		String sql = "From MRHKHZXL";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZXL> resultList = query.getResultList();
		return resultList;
	}

}
