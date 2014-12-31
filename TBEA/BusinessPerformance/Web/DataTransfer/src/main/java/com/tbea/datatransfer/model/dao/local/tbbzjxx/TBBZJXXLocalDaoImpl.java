package com.tbea.datatransfer.model.dao.local.tbbzjxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.TBBZJXXLocal;

@Transactional("transactionManager")
public class TBBZJXXLocalDaoImpl extends
		AbstractReadWriteDaoImpl<TBBZJXXLocal> implements TBBZJXXLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TBBZJXXLocal> getAllTBBZJXXLocal() {
		String sql = "From TBBZJXXLocal";
		Query query = getEntityManager().createQuery(sql);
		List<TBBZJXXLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteTBBZJXXLocalByQY(int qybh) {
		String sql = "Delete From TBBZJXXLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}