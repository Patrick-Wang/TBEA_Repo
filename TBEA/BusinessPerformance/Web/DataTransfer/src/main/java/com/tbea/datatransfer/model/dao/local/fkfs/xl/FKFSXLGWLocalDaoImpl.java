package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSXLGWLocal;

@Transactional("transactionManager")
public class FKFSXLGWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSXLGWLocal> implements FKFSXLGWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLGWLocal> getAllFKFSXLGWLocal() {
		String sql = "From FKFSXLGWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLGWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSXLGWLocalByQY(int qybh) {
		String sql = "Delete From FKFSXLGWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}