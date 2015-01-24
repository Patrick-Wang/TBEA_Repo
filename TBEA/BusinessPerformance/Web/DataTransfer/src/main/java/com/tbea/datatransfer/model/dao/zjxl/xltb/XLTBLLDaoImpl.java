package com.tbea.datatransfer.model.dao.zjxl.xltb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.XLTBXL;

@Transactional("transactionManagerll")
public class XLTBLLDaoImpl extends AbstractReadOnlyDaoImpl<XLTBXL> implements
		XLTBXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLTBXL> getAllXLTB() {
		String sql = "From XLTBXL";
		Query query = getEntityManager().createQuery(sql);
		List<XLTBXL> resultList = query.getResultList();
		return resultList;
	}

}
