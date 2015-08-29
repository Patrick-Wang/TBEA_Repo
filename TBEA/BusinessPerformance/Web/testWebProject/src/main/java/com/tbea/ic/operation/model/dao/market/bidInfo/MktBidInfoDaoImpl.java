package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
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
	
	@Override
	public List<MktBidInfo> getData(String companyName) {
		Query q;
		if(companyName.equals("全部公司"))
		{
			q = manager.createQuery(
					"from MktBidInfo");
		}else{
			q = manager.createQuery(
					"from MktBidInfo where company_name like:comp order by bid_no desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		
		return q.getResultList();
	}

}
