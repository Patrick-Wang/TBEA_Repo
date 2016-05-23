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
public class SystemExtendAuthDaoImpl extends AbstractReadWriteDaoImpl<ExtendAuthority> implements SystemExtendAuthDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ExtendAuthority> getDataListByAccAuthType(int account_id, int authType) {
		Query q = this.getEntityManager().createQuery("from ExtendAuthority where account.id = :id and authType=:type");
		q.setParameter("id", account_id);
		q.setParameter("type", authType);
		return q.getResultList();
	}
}
