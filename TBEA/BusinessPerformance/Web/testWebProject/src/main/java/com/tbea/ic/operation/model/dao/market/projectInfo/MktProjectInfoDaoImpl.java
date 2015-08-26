package com.tbea.ic.operation.model.dao.market.projectInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.MktProjectInfo;

@Repository
@Transactional("transactionManager")
public class MktProjectInfoDaoImpl implements MktProjectInfoDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktProjectInfo mktObject) {
		manager.merge(mktObject);
	}
	
	
}
