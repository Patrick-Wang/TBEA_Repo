package com.tbea.datatransfer.model.dao.zjdl.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.HTXXDL;

@Transactional("transactionManagerdl")
public class HTXXDLDaoImpl extends AbstractReadOnlyDaoImpl<HTXXDL> implements
		HTXXDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXXDL> getAllHTXXDL() {
		String sql = "From HTXXDL";
		Query query = getEntityManager().createQuery(sql);
		List<HTXXDL> resultList = query.getResultList();
		return resultList;
	}

}
