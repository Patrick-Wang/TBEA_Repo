package com.tbea.datatransfer.model.dao.local.jygk.sjzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.SJZBLocal;

@Transactional("transactionManager")
public class SJZBLocalDaoImpl extends AbstractReadWriteDaoImpl<SJZBLocal>
		implements SJZBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SJZBLocal> getAllSJZBLocal() {
		String sql = "From SJZBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<SJZBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateSJZBLocal() {
		String sql = "truncate table jygk_sjzb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteSJZBLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From SJZBLocal Where dwid in (:dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

}
