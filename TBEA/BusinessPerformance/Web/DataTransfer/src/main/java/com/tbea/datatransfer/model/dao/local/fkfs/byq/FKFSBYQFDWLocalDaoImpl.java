package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSBYQFDWLocal;

@Transactional("transactionManager")
public class FKFSBYQFDWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSBYQFDWLocal> implements FKFSBYQFDWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQFDWLocal> getAllFKFSBYQFDWLocal() {
		String sql = "From FKFSBYQFDWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQFDWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSBYQFDWLocalByQY(int qybh) {
		String sql = "Delete From FKFSBYQFDWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}