package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.ExtendAuthority;

@Repository
@Transactional("transactionManager")
public class SystemExtendAuthDaoImpl implements SystemExtendAuthDao{

	EntityManager entityManager;
	
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public List<ExtendAuthority> getDataListByAccAuthType(int account_id, int authType) {
		Query q = entityManager.createQuery("from ExtendAuthority where account.id = :id and authType=:type");
		q.setParameter("id", account_id);
		q.setParameter("type", authType);
		return q.getResultList();
	}
}
