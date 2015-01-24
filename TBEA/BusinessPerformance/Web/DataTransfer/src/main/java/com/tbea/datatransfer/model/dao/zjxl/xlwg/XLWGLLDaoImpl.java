package com.tbea.datatransfer.model.dao.zjxl.xlwg;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.XLWGXL;

@Transactional("transactionManagerll")
public class XLWGLLDaoImpl extends AbstractReadOnlyDaoImpl<XLWGXL> implements
		XLWGXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
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
