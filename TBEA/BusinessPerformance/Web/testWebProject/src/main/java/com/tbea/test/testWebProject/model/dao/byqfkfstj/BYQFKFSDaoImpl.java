package com.tbea.test.testWebProject.model.dao.byqfkfstj;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.BYQFDWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQGWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQNWFKFS;
@Repository
@Transactional("transactionManager")
public class BYQFKFSDaoImpl implements BYQFKFSDao {
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<BYQFDWFKFS> getFdwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQFDWFKFS where ny = :date");
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}

	@Override
	public List<BYQGWFKFS> getGwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQGWFKFS where ny = :date");
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}

	@Override
	public List<BYQNWFKFS> getNwfs(Date d) {
		Query q = entityManager.createQuery(
				"from BYQNWFKFS where ny = :date");
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}
	
}
