package com.tbea.datatransfer.model.dao.zjbyq.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.HTXXBYQ;

@Transactional("transactionManagerxb")
public class HTXXXBDaoImpl extends AbstractReadOnlyDaoImpl<HTXXBYQ> implements
		HTXXBYQDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXXBYQ> getAllHTXX() {
		String sql = "From HTXXBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<HTXXBYQ> resultList = query.getResultList();
		return resultList;
	}

}
