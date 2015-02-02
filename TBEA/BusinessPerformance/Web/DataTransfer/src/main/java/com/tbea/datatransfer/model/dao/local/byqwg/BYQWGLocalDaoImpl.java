package com.tbea.datatransfer.model.dao.local.byqwg;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.BYQWGLocal;

@Transactional("transactionManager")
public class BYQWGLocalDaoImpl extends AbstractReadWriteDaoImpl<BYQWGLocal>
		implements BYQWGLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void deleteBYQWGLocalByQY(int qybh) {
		String sql = "Delete From BYQWGLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}
