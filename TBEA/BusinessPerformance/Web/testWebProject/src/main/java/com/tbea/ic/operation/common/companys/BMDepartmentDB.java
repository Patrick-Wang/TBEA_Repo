package com.tbea.ic.operation.common.companys;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BMDepartmentDB extends AbstractOrganization {
	

	@Transactional("transactionManager")
	@PersistenceContext(unitName = "localDB")
	void setEntityManager(EntityManager entityManager){
		
	}
	

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
