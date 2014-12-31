package com.tbea.datatransfer.model.dao.zjdl.xlwg;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.XLWGDL;

@Transactional("transactionManagerdl")
public class XLWGDLDaoImpl extends AbstractReadOnlyDaoImpl<XLWGDL> implements
		XLWGDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLWGDL> getAllXLWGDL() {
		String sql = "From XLWGDL";
		Query query = getEntityManager().createQuery(sql);
		List<XLWGDL> resultList = query.getResultList();
		return resultList;
	}

}
