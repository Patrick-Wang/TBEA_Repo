package com.tbea.datatransfer.model.dao.zjdl.tbbzjxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.TBBZJXXXL;

@Transactional("transactionManagerdl")
public class TBBZJXXDLDaoImpl extends AbstractReadOnlyDaoImpl<TBBZJXXXL>
		implements TBBZJXXDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TBBZJXXXL> getAllTBBZJXX() {
		String sql = "From TBBZJXXXL";
		Query query = getEntityManager().createQuery(sql);
		List<TBBZJXXXL> resultList = query.getResultList();
		return resultList;
	}

}
