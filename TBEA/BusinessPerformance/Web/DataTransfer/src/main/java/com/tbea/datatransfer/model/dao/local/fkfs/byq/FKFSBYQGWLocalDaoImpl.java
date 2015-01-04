package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSBYQGWLocal;

@Transactional("transactionManager")
public class FKFSBYQGWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSBYQGWLocal> implements FKFSBYQGWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQGWLocal> getAllFKFSBYQGWLocal() {
		String sql = "From FKFSBYQGWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQGWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSBYQGWLocalByQY(int qybh) {
		String sql = "Delete From FKFSBYQGWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}