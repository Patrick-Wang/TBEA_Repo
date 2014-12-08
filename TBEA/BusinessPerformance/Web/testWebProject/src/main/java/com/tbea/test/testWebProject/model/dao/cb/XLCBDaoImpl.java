package com.tbea.test.testWebProject.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.entity.CBXLTBDD;
import com.tbea.test.testWebProject.model.entity.CBXLWGDD;
import com.tbea.test.testWebProject.model.entity.CBXLZXDD;

@Repository
@Transactional("transactionManager")
public class XLCBDaoImpl implements XLCBDao{
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<CBXLTBDD> getTbdd() {
		Query q = entityManager.createQuery(
				"from CBXLTBDD");
		return q.getResultList();
	}

	@Override
	public List<CBXLZXDD> getZxdd() {
		Query q = entityManager.createQuery(
				"from CBXLZXDD");
		return q.getResultList();
	}

	@Override
	public List<CBXLWGDD> getWgdd() {
		Query q = entityManager.createQuery(
				"from CBXLWGDD");
		return q.getResultList();
	}
}
