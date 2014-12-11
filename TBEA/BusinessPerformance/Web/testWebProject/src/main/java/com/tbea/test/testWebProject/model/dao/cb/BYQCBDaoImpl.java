package com.tbea.test.testWebProject.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.entity.CBBYQTBDD;
import com.tbea.test.testWebProject.model.entity.CBBYQWGDD;
import com.tbea.test.testWebProject.model.entity.CBBYQZXDD;

@Repository
@Transactional("transactionManager")
public class BYQCBDaoImpl implements  BYQCBDao{
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<CBBYQTBDD> getTbdd() {
		Query q = entityManager.createQuery(
				"from CBBYQTBDD");
		return q.getResultList();
	}

	@Override
	public List<CBBYQZXDD> getZxdd() {
		Query q = entityManager.createQuery(
				"from CBBYQZXDD");
		return q.getResultList();
	}

	@Override
	public List<CBBYQWGDD> getWgdd() {
		Query q = entityManager.createQuery(
				"from CBBYQWGDD");
		return q.getResultList();
	}

	@Override
	public CBBYQTBDD getTbddById(Integer tbcpbh) {
		Query q = entityManager.createQuery(
				"from CBBYQTBDD where id = :tbcpbh");
		q.setParameter("tbcpbh", tbcpbh);
		List<CBBYQTBDD> tbdds = q.getResultList();
		if (!tbdds.isEmpty()){
			return tbdds.get(0);
		}
		return null;
	}

	@Override
	public CBBYQZXDD getZxddById(Integer zxcpbh) {
		Query q = entityManager.createQuery(
				"from CBBYQZXDD where id = :zxcpbh");
		q.setParameter("zxcpbh", zxcpbh);
		List<CBBYQZXDD> zxdds = q.getResultList();
		if (!zxdds.isEmpty()){
			return zxdds.get(0);
		}
		return null;
	}
}
