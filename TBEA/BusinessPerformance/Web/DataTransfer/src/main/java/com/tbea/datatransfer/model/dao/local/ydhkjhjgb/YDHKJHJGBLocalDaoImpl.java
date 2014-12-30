package com.tbea.datatransfer.model.dao.local.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;

@Transactional("transactionManager")
public class YDHKJHJGBLocalDaoImpl extends AbstractReadWriteDaoImpl<YDHKJHJGBLocal>
		implements YDHKJHJGBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBLocal> getAllYDHKJHJGBLocal() {
		String sql = "From YDHKJHJGBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteYDHKJHJGBLocalByQY(int qybh) {
		String sql = "Delete From YDHKJHJGBLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}