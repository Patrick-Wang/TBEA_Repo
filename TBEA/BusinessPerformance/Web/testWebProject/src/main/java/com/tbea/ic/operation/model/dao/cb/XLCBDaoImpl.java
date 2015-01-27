package com.tbea.ic.operation.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBXLTBDD;
import com.tbea.ic.operation.model.entity.CBXLWGDD;
import com.tbea.ic.operation.model.entity.CBXLZXDD;

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

	@Override
	public boolean containsTbCompany(Company comp) {
		Query q = entityManager
				.createQuery("SELECT t FROM CBXLTBDD t, XMXX x where t.xmbh = x.xmbh and x.ddszdw = :comp");
		q.setParameter("comp", "0" + comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}

	@Override
	public CBXLWGDD getLatestWgdd() {
		Query q = entityManager.createQuery(
				"from CBXLWGDD order by wgsj desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<CBXLWGDD> wgdds = q.getResultList();
		if (!wgdds.isEmpty()){
			return wgdds.get(0);
		}
		return null;
	}
}
