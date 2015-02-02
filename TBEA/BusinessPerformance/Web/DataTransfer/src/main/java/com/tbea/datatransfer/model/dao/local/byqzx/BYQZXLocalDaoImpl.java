package com.tbea.datatransfer.model.dao.local.byqzx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.BYQZXLocal;

@Transactional("transactionManager")
public class BYQZXLocalDaoImpl extends AbstractReadWriteDaoImpl<BYQZXLocal>
		implements BYQZXLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void deleteBYQZXLocalByQY(int qybh) {
		String sql = "Delete From BYQZXLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}
