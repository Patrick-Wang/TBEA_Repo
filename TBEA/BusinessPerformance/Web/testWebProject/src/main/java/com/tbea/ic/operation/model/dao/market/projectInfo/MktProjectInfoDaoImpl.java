package com.tbea.ic.operation.model.dao.market.projectInfo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
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
	
	@Override
	public List<MktProjectInfo> getData(String companyName) {
		Query q;
		if(companyName.equals("全部公司"))
		{
			q = manager.createQuery(
					"from MktProjectInfo");
		}else{
			q = manager.createQuery(
					"from MktProjectInfo where company_name like:comp order by project_no desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		return q.getResultList();
	}
}
