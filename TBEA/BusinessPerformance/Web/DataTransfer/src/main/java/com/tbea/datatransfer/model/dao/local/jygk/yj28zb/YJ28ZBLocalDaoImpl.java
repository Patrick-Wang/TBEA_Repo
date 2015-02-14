package com.tbea.datatransfer.model.dao.local.jygk.yj28zb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.YJ28ZBLocal;

@Transactional("transactionManager")
public class YJ28ZBLocalDaoImpl extends AbstractReadWriteDaoImpl<YJ28ZBLocal>
		implements YJ28ZBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YJ28ZBLocal> getAllYJ28ZBLocal() {
		String sql = "From YJ28ZBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YJ28ZBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateYJ28ZBLocal() {
		String sql = "truncate table jygk_yj28zb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteYJ28ZBLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From YJ28ZBLocal Where dwid in (:dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

}
