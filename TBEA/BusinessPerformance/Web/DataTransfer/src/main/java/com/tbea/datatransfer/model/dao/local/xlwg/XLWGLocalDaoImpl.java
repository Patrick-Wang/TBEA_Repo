package com.tbea.datatransfer.model.dao.local.xlwg;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.XLWGLocal;

@Transactional("transactionManager")
public class XLWGLocalDaoImpl extends
		AbstractReadWriteDaoImpl<XLWGLocal> implements XLWGLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<XLWGLocal> getAllXLWGLocal() {
		String sql = "From XLWGLocal";
		Query query = getEntityManager().createQuery(sql);
		List<XLWGLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteXLWGLocalByQY(int qybh) {
		String sql = "Delete From XLWGLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}