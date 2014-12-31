package com.tbea.datatransfer.model.dao.local.xm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.XMLocal;

@Transactional("transactionManager")
public class XMLocalDaoImpl extends
		AbstractReadWriteDaoImpl<XMLocal> implements XMLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XMLocal> getAllXMLocal() {
		String sql = "From XMLocal";
		Query query = getEntityManager().createQuery(sql);
		List<XMLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteXMLocalByQY(int qybh) {
		String sql = "Delete From XMLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}