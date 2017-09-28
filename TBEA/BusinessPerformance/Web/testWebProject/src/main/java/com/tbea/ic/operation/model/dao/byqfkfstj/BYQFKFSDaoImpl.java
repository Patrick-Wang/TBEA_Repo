package com.tbea.ic.operation.model.dao.byqfkfstj;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.BYQFDWFKFS;
import com.tbea.ic.operation.model.entity.BYQGWFKFS;
import com.tbea.ic.operation.model.entity.BYQNWFKFS;
import com.util.tools.DateUtil;
@Repository
@Transactional("transactionManager")
public class BYQFKFSDaoImpl implements BYQFKFSDao {
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<BYQFDWFKFS> getFdwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQFDWFKFS where ny = :date");
		q.setParameter("date", DateUtil.month1(d));
		return q.getResultList();
	}

	@Override
	public List<BYQGWFKFS> getGwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQGWFKFS where ny = :date");
		q.setParameter("date", DateUtil.month1(d));
		return q.getResultList();
	}

	@Override
	public List<BYQNWFKFS> getNwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQNWFKFS where ny = :date");
		q.setParameter("date", DateUtil.month1(d));
		return q.getResultList();
	}

	@Override
	public BYQFDWFKFS getLatestFdwfkfs() {
		Query q = entityManager.createQuery(
				"from BYQFDWFKFS order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<BYQFDWFKFS> fkfs = q.getResultList();
		if (!fkfs.isEmpty()){
			return fkfs.get(0);
		}
		return null;
	}

	@Override
	public BYQGWFKFS getLatestGwfkfs() {
		Query q = entityManager.createQuery(
				"from BYQGWFKFS order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<BYQGWFKFS> fkfs = q.getResultList();
		if (!fkfs.isEmpty()){
			return fkfs.get(0);
		}
		return null;
	}

	@Override
	public BYQNWFKFS getLatestNwfkfs() {
		Query q = entityManager.createQuery(
				"from BYQNWFKFS order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<BYQNWFKFS> fkfs = q.getResultList();
		if (!fkfs.isEmpty()){
			return fkfs.get(0);
		}
		return null;
	}
	
}
