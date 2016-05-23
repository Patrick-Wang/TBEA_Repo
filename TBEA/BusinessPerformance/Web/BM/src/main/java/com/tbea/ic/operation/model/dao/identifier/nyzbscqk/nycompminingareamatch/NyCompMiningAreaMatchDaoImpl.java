package com.tbea.ic.operation.model.dao.identifier.nyzbscqk.nycompminingareamatch;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyCompMiningAreaMatchEntity;



@Repository(NyCompMiningAreaMatchDaoImpl.NAME)
@Transactional("transactionManager")
public class NyCompMiningAreaMatchDaoImpl extends AbstractReadWriteDaoImpl<NyCompMiningAreaMatchEntity> implements NyCompMiningAreaMatchDao  {
	public final static String NAME = "NyCompMiningAreaMatchDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	public List<NyCompMiningAreaMatchEntity> getMiningArea(Company company){
		Query q = this.getEntityManager().createQuery("from NyCompMiningAreaMatchEntity where dwid=:dwid");
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}
	
}
