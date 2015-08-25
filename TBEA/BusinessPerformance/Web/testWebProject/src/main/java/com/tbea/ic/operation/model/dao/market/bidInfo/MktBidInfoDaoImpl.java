package com.tbea.ic.operation.model.dao.market.bidInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.MktBidInfo;

@Repository
@Transactional("transactionManager")
public class MktBidInfoDaoImpl implements MktBidInfoDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktBidInfo mktBidInfo) {
		manager.merge(mktBidInfo);
	}

}
