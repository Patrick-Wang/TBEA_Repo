package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyNdjhzbDaoImpl extends AbstractReadWriteDaoImpl<NDJHZB> implements ZzyNdjhzbDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<NDJHZB> getDataListByDwDate(String dwxxs,String zbidstrs,int nf) {		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(ndjhz) as ndjhz from NDJHZB where dwxx.id in (" + dwxxs + ") and nf = :nf and zbxx.id in (" + zbidstrs + ")  group by zbxx.id");
		q.setParameter("nf", nf);
		List<Object[]> objectList=q.getResultList();
		List<NDJHZB> retList=new ArrayList<NDJHZB>();
		for(Object[] oa:objectList){
			NDJHZB j=new NDJHZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setNdjhz((Double)oa[1]);			
			retList.add(j);
		}
		return retList;
	}	
}
