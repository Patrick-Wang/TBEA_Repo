package com.tbea.datatransfer.model.dao.zjdl.xlwg;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.XLWGXL;

@Transactional("transactionManagerdl")
public class XLWGDLDaoImpl extends AbstractReadOnlyDaoImpl<XLWGXL> implements
		XLWGDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLWGXL> getAllXLWG() {
		String sql = "From XLWGXL";
		Query query = getEntityManager().createQuery(sql);
		List<XLWGXL> resultList = query.getResultList();
		return resultList;
	}

}
