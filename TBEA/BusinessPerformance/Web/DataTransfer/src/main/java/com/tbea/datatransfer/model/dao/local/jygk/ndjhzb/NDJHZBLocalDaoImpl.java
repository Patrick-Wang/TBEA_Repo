package com.tbea.datatransfer.model.dao.local.jygk.ndjhzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.NDJHZBLocal;

@Transactional("transactionManager")
public class NDJHZBLocalDaoImpl extends AbstractReadWriteDaoImpl<NDJHZBLocal>
		implements NDJHZBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NDJHZBLocal> getAllNDJHZBLocal() {
		String sql = "From NDJHZBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<NDJHZBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateNDJHZBLocal() {
		String sql = "truncate table jygk_ndjhzb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteNDJHZBLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From NDJHZBLocal Where dwid in :(dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

}
