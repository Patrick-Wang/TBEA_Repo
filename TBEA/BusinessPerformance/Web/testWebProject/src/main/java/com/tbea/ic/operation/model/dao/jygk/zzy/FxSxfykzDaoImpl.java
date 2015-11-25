package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxSxfykz;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxSxfykzDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxSxfykz> implements FxSxfykzDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyFxSxfykz> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zbxxid,sum(ndjhfyl) as ndjhfyl from JygkZzyFxSxfykz where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf group by zbxxid");		
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyFxSxfykz> retList=new ArrayList<JygkZzyFxSxfykz>();
		for(Object[] oa:objectList){
			JygkZzyFxSxfykz j=new JygkZzyFxSxfykz();
			j.setZbxxid((int)oa[0]);
			j.setNdjhfyl((BigDecimal)oa[1]);			
			retList.add(j);
		}
		return retList;
	}
}
