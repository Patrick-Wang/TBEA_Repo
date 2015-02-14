package com.tbea.datatransfer.model.dao.local.jygk.ydjhzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.YDJHZBLocal;

@Transactional("transactionManager")
public class YDJHZBLocalDaoImpl extends AbstractReadWriteDaoImpl<YDJHZBLocal>
		implements YDJHZBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDJHZBLocal> getAllYDJHZBLocal() {
		String sql = "From YDJHZBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YDJHZBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateYDJHZBLocal() {
		String sql = "truncate table jygk_ydjhzb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteYDJHZBLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From YDJHZBLocal Where dwid in (:dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

}
