package com.tbea.datatransfer.model.dao.local.ydsjhkqk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;

@Transactional("transactionManager")
public class YDSJHKQKLocalDaoImpl extends
		AbstractReadWriteDaoImpl<YDSJHKQKLocal> implements YDSJHKQKLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDSJHKQKLocal> getAllYDSJHKQKLocal() {
		String sql = "From YDSJHKQKLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YDSJHKQKLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteYDSJHKQKLocalByQY(int qybh) {
		String sql = "Delete From YDSJHKQKLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}