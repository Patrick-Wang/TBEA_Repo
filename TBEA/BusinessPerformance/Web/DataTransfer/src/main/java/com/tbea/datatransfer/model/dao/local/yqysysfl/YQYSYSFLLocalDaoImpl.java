package com.tbea.datatransfer.model.dao.local.yqysysfl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YQYSYSFLLocal;

@Transactional("transactionManager")
public class YQYSYSFLLocalDaoImpl extends
		AbstractReadWriteDaoImpl<YQYSYSFLLocal> implements YQYSYSFLLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YQYSYSFLLocal> getAllYQYSYSFLLocal() {
		String sql = "From YQYSYSFLLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YQYSYSFLLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteYQYSYSFLLocalByQY(int qybh) {
		String sql = "Delete From YQYSYSFLLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}