package com.tbea.datatransfer.model.dao.local.xltb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.XLTBLocal;

@Transactional("transactionManager")
public class XLTBLocalDaoImpl extends
		AbstractReadWriteDaoImpl<XLTBLocal> implements XLTBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLTBLocal> getAllXLTBLocal() {
		String sql = "From XLTBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<XLTBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteXLTBLocalByQY(int qybh) {
		String sql = "Delete From XLTBLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}