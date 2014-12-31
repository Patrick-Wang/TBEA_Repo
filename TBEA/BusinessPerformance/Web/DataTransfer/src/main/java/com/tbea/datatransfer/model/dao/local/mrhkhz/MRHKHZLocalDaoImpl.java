package com.tbea.datatransfer.model.dao.local.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;

@Transactional("transactionManager")
public class MRHKHZLocalDaoImpl extends AbstractReadWriteDaoImpl<MRHKHZLocal>
		implements MRHKHZLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZLocal> getAllMRHKHZLocal() {
		String sql = "From MRHKHZLocal";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteMRHKHZLocalByQY(int qybh) {
		String sql = "Delete From MRHKHZLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}