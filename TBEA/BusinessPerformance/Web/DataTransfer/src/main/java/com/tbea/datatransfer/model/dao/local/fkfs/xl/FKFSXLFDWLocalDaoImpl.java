package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.FKFSXLFDWLocal;

@Transactional("transactionManager")
public class FKFSXLFDWLocalDaoImpl extends
		AbstractReadWriteDaoImpl<FKFSXLFDWLocal> implements FKFSXLFDWLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLFDWLocal> getAllFKFSXLFDWLocal() {
		String sql = "From FKFSXLFDWLocal";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLFDWLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteFKFSXLFDWLocalByQY(int qybh) {
		String sql = "Delete From FKFSXLFDWLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}