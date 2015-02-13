package com.tbea.datatransfer.model.dao.local.jygk.yj20zb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.YJ20ZBLocal;

@Transactional("transactionManager")
public class YJ20ZBLocalDaoImpl extends AbstractReadWriteDaoImpl<YJ20ZBLocal>
		implements YJ20ZBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YJ20ZBLocal> getAllYJ20ZBLocal() {
		String sql = "From YJ20ZBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YJ20ZBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateYJ20ZBLocal() {
		String sql = "truncate table jygk_yj20zb";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteYJ20ZBLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From YJ20ZBLocal Where dwid in :(dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

}
