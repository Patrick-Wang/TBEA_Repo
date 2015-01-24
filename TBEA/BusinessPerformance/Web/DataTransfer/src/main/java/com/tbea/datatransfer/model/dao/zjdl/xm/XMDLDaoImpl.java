package com.tbea.datatransfer.model.dao.zjdl.xm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.XMXL;

@Transactional("transactionManagerdl")
public class XMDLDaoImpl extends AbstractReadOnlyDaoImpl<XMXL> implements
		XMDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XMXL> getAllXM() {
		String sql = "From XMXL";
		Query query = getEntityManager().createQuery(sql);
		List<XMXL> resultList = query.getResultList();
		return resultList;
	}

}
