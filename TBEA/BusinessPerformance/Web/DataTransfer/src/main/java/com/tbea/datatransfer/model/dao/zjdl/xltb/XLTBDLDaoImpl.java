package com.tbea.datatransfer.model.dao.zjdl.xltb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.XLTBDL;

@Transactional("transactionManagerdl")
public class XLTBDLDaoImpl extends AbstractReadOnlyDaoImpl<XLTBDL> implements
		XLTBDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLTBDL> getAllXLTBDL() {
		String sql = "From XLTBDL";
		Query query = getEntityManager().createQuery(sql);
		List<XLTBDL> resultList = query.getResultList();
		return resultList;
	}

}
