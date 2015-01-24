package com.tbea.datatransfer.model.dao.zjdl.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.HTXXXL;

@Transactional("transactionManagerdl")
public class HTXXDLDaoImpl extends AbstractReadOnlyDaoImpl<HTXXXL> implements
		HTXXDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
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
