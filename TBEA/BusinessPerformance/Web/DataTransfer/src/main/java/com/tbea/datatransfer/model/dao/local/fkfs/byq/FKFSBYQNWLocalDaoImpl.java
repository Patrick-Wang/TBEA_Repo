package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;

@Transactional("transactionManager")
public class FKFSBYQNWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSBYQNWLocal> implements FKFSBYQNWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQNWLocal> getAllFKFSBYQNWLocal() {
		String sql = "From FKFSBYQNWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQNWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSBYQNWLocalByQY(int qybh) {
		String sql = "Delete From FKFSBYQNWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}