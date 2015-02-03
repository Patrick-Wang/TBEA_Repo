package com.tbea.datatransfer.model.dao.zjbyq.yqysysfl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.YQYSYSFLBYQ;

@Transactional("transactionManagertb2")
public class YQYSYSFLTBDaoImpl extends AbstractReadOnlyDaoImpl<YQYSYSFLBYQ>
		implements YQYSYSFLBYQDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YQYSYSFLBYQ> getAllYQYSYSFL() {
		String sql = "From YQYSYSFLBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<YQYSYSFLBYQ> resultList = query.getResultList();
		return resultList;
	}

}
