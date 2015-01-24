package com.tbea.datatransfer.model.dao.zjxl.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.HTXXXL;

@Transactional("transactionManagerxl")
public class HTXXXLDaoImpl extends AbstractReadOnlyDaoImpl<HTXXXL> implements
		HTXXXLDao {

	@Override
	@PersistenceContext(unitName = "xlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXXXL> getAllHTXX() {
		String sql = "From HTXXXL";
		Query query = getEntityManager().createQuery(sql);
		List<HTXXXL> resultList = query.getResultList();
		return resultList;
	}

}
