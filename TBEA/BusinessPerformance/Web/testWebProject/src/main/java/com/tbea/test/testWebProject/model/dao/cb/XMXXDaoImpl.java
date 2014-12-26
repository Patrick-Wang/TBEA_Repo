package com.tbea.test.testWebProject.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.XMXX;
@Repository
@Transactional("transactionManager")
public class XMXXDaoImpl implements XMXXDao{
	
	
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;
	
	public XMXX getXmxxByBh(String bh){
		Query q = entityManager.createQuery(
				"from XMXX where xmbh = :xmbh");
		q.setParameter("xmbh", bh);
		List<XMXX> ret =  q.getResultList();
		if (!ret.isEmpty())
		{
			return ret.get(0);
		}
		return null;
	}

	@Override
	public boolean hasCompany(Company company) {
		Query q = entityManager
				.createQuery("from XMXX CAST(ddzdw as int) where ddszdw = :comp");
		q.setParameter("comp", "0" + company.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}
}
