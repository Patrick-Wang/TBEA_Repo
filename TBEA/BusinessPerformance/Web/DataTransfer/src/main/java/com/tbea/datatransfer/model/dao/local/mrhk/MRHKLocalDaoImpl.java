package com.tbea.datatransfer.model.dao.local.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.MRHKLocal;

//@Repository
@Transactional("transactionManager")
public class MRHKLocalDaoImpl extends AbstractReadWriteDaoImpl<MRHKLocal> implements
		MRHKLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKLocal> getAllMRHKLocal() {
		String sql = "From MRHKLocal";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateMRHKLocal() {
		String sql = "truncate table yszk_zj_mehk";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteMRHKLocalByQY(int qybh) {
		String sql = "Delete From MRHKLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}
