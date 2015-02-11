package com.tbea.ic.operation.model.dao.qxgl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.QXGL;
@Repository
@Transactional("transactionManager")
public class QXGLDaoImpl  extends AbstractReadWriteDaoImpl<QXGL> implements QXGLDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public Long getJhzlrCount(Account account) {
		Query q = getEntityManager().createQuery("select count(q) from QXGL q where q.account.id = :accountId and q.jhzlr = 1");
		q.setParameter("accountId", account.getId());
		return (Long) q.getSingleResult();
	}

	@Override
	public Long getSjzlrCount(Account account) {
		Query q = getEntityManager().createQuery("select count(q) from QXGL q where q.account.id = :accountId and q.sjzlr = 1");
		q.setParameter("accountId", account.getId());
		return (Long)q.getSingleResult();
	}

	@Override
	public Long getJhzshCount(Account account) {
		Query q = getEntityManager().createQuery("select count(q) from QXGL q where q.account.id = :accountId and q.jhzsh = 1");
		q.setParameter("accountId", account.getId());
		return (Long)q.getSingleResult();
	}

	@Override
	public Long getSjzshCount(Account account) {
		Query q = getEntityManager().createQuery("select count(q) from QXGL q where q.account.id = :accountId and q.sjzsh = 1");
		q.setParameter("accountId", account.getId());
		return (Long)q.getSingleResult();
	}

	@Override
	public List<QXGL> getSjzlr(Account account) {
		Query q = getEntityManager().createQuery("from QXGL where account.id = :accountId and sjzlr = 1");
		q.setParameter("accountId", account.getId());
		return q.getResultList();
	}

	@Override
	public List<QXGL> getJhzlr(Account account) {
		Query q = getEntityManager().createQuery("from QXGL where account.id = :accountId and jhzlr = 1");
		q.setParameter("accountId", account.getId());
		return q.getResultList();
	}

	@Override
	public List<QXGL> getSjzsh(Account account) {
		Query q = getEntityManager().createQuery("from QXGL where account.id = :accountId and jhzsh = 1");
		q.setParameter("accountId", account.getId());
		return q.getResultList();
	}

	@Override
	public List<QXGL> getJhzsh(Account account) {
		Query q = getEntityManager().createQuery("from QXGL where account.id = :accountId and sjzsh = 1");
		q.setParameter("accountId", account.getId());
		return q.getResultList();
	}
}
