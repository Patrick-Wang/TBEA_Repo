package com.tbea.ic.operation.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.CBBYQTBDD;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBBYQZXDD;
import com.tbea.ic.operation.model.entity.RHKXX;

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
	
	
	@Override
	public boolean containsTbCompany(Company company) {
		Query q = entityManager
				.createQuery("SELECT c FROM CBBYQTBDD c, XMXX x where c.xmxx = x.xmbh and x.ddszdw = :comp");
		q.setParameter("comp", "0" + company.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}
	
	@Override
	public boolean containsZxCompany(Company company) {
		Query q = entityManager
				.createQuery("SELECT z FROM CBBYQZXDD z, CBBYQTBDD t, XMXX x where z.tbcpbh = t.id and t.xmxx = x.xmbh and x.ddszdw = :comp");
		q.setParameter("comp", "0" + company.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}
	
	@Override
	public boolean containsWgCompany(Company company) {
		Query q = entityManager
				.createQuery("SELECT w FROM CBBYQWGDD w, CBBYQZXDD z, CBBYQTBDD t, XMXX x where w.zxcpbh = z.id and z.tbcpbh = t.id and t.xmxx = x.xmbh and x.ddszdw = :comp");
		q.setParameter("comp", "0" + company.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}

	@Override
	public CBBYQWGDD getLatestWgdd() {
		Query q = entityManager.createQuery(
				"from CBBYQWGDD order by wgsj desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<CBBYQWGDD> wgdds = q.getResultList();
		if (!wgdds.isEmpty()){
			return wgdds.get(0);
		}
		return null;
	}
}
