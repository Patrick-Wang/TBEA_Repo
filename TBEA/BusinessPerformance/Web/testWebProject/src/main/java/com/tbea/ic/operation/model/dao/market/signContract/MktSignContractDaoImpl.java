package com.tbea.ic.operation.model.dao.market.signContract;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
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
	
	@Override
	public List<MktSignContract> getData(String companyName) {
		Query q;
		if(companyName.equals("股份公司"))
		{
			q = manager.createQuery(
					"from MktSignContract order by startdate desc");
		}else{
			q = manager.createQuery(
					"from MktSignContract where company_name like :comp order by startdate desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		return q.getResultList();
	}

	@Override
	public MktSignContract getById(String contractNo) {
		Query q = manager.createQuery(
				"from MktSignContract where contractNo = :contractNo");
		q.setParameter("contractNo", contractNo);
		List<MktSignContract> mbis = q.getResultList();
		if (mbis.isEmpty()){
			return null;
		}
		return mbis.get(0);
	}

	@Override
	public void remove(String key) {
		MktSignContract info = this.getById(key);
		if (null != info){
			manager.remove(info);
		}
	}
}
