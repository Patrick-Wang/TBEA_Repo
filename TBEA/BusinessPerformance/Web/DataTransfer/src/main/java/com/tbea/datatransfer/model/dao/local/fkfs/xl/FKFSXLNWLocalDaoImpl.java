package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSXLNWLocal;

@Transactional("transactionManager")
public class FKFSXLNWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSXLNWLocal> implements FKFSXLNWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLNWLocal> getAllFKFSXLNWLocal() {
		String sql = "From FKFSXLNWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLNWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSXLNWLocalByQY(int qybh) {
		String sql = "Delete From FKFSXLNWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}