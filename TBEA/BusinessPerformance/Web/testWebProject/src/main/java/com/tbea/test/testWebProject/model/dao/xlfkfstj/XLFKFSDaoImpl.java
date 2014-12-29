package com.tbea.test.testWebProject.model.dao.xlfkfstj;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.XLFDWFKFS;
import com.tbea.test.testWebProject.model.entity.XLGWFKFS;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;

@Repository
@Transactional("transactionManager")
public class XLFKFSDaoImpl implements XLFKFSDao {
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<XLFDWFKFS> getFdwfkfs(Date d, Company comp) {
		Query q = entityManager.createQuery(
				"from XLFDWFKFS where gsbm = :compId and ny= :date");
		q.setParameter("compId", "0" + comp.getId());
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}

	@Override
	public List<XLGWFKFS> getGwfkfs(Date d, Company comp) {
		Query q = entityManager.createQuery(
				"from XLGWFKFS where gsbm = :compId and ny= :date");
		q.setParameter("compId", "0" + comp.getId());
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}

	@Override
	public List<XLNWFKFS> getNwfkfs(Date d, Company comp) {
		Query q = entityManager.createQuery(
				"from XLNWFKFS where gsbm = :compId and ny= :date");
		q.setParameter("compId", "0" + comp.getId());
		q.setParameter("date", Util.format(d));
		return q.getResultList();
	}

	@Override
	public boolean fdwContainsCompany(Company comp) {
		Query q = entityManager.createQuery(
				"select count(x) from XLFDWFKFS x where x.gsbm = :compId");
		q.setParameter("compId", "0" + comp.getId());
		return ((Long)(q.getResultList().get(0))) > 0;
	}

	@Override
	public boolean gwContainsCompany(Company comp) {
		Query q = entityManager.createQuery(
				"select count(x) from XLGWFKFS x where x.gsbm = :compId");
		q.setParameter("compId", "0" + comp.getId());
		return ((Long)(q.getResultList().get(0))) > 0;
	}

	@Override
	public boolean nwContainsCompany(Company comp) {
		Query q = entityManager.createQuery(
				"select count(x) from XLNWFKFS x where x.gsbm = :compId");
		q.setParameter("compId", "0" + comp.getId());
		return ((Long)(q.getResultList().get(0))) > 0;
	}
}
