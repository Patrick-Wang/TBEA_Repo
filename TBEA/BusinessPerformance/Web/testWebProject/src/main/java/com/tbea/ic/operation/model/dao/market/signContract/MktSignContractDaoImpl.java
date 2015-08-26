package com.tbea.ic.operation.model.dao.market.signContract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktSignContract;

@Repository
@Transactional("transactionManager")
public class MktSignContractDaoImpl implements MktSignContractDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktSignContract mktObject) {
		manager.merge(mktObject);	
	}
	
	
}
