package com.tbea.datatransfer.model.dao.local.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.ZTYSZKFXBLocal;

@Transactional("transactionManager")
public class ZTYSZKFXBLocalDaoImpl extends AbstractReadWriteDaoImpl<ZTYSZKFXBLocal>
		implements ZTYSZKFXBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBLocal> getAllZTYSZKFXBLocal() {
		String sql = "From ZTYSZKFXBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteZTYSZKFXBLocalByQY(int qybh) {
		String sql = "Delete From ZTYSZKFXBLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

}