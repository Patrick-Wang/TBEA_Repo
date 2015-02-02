package com.tbea.datatransfer.model.dao.local.byqtb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.BYQTBLocal;

@Transactional("transactionManager")
public class BYQTBLocalDaoImpl extends AbstractReadWriteDaoImpl<BYQTBLocal>
		implements BYQTBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void deleteBYQTBLocalByQY(int qybh) {
		String sql = "Delete From BYQTBLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}
