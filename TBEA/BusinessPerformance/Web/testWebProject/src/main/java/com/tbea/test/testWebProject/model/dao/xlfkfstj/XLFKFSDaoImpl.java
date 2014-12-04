package com.tbea.test.testWebProject.model.dao.xlfkfstj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.entity.XLFDWFKFS;
import com.tbea.test.testWebProject.model.entity.XLGWFKFS;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;

@Repository
@Transactional("transactionManager")
public class XLFKFSDaoImpl implements XLFKFSDao {
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<XLFDWFKFS> getFdwfkfs() {
		Query q = entityManager.createQuery(
				"from XLFDWFKFS");
		return q.getResultList();
	}

	@Override
	public List<XLGWFKFS> getGwfkfs() {
		Query q = entityManager.createQuery(
				"from XLGWFKFS");
		return q.getResultList();
	}

	@Override
	public List<XLNWFKFS> getNwfkfs() {
		Query q = entityManager.createQuery(
				"from XLNWFKFS");
		return q.getResultList();
	}
}
