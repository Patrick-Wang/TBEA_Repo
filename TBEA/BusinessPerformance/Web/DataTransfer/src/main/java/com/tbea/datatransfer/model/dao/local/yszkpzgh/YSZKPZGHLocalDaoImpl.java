package com.tbea.datatransfer.model.dao.local.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YSZKPZGHLocal;

@Transactional("transactionManager")
public class YSZKPZGHLocalDaoImpl extends
		AbstractReadWriteDaoImpl<YSZKPZGHLocal> implements YSZKPZGHLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKPZGHLocal> getAllYSZKPZGHLocal() {
		String sql = "From YSZKPZGHLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKPZGHLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteYSZKPZGHLocalByQY(int qybh) {
		String sql = "Delete From YSZKPZGHLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}