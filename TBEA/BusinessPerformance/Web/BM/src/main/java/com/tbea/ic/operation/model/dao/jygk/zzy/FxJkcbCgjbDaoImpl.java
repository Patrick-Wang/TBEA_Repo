package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbCgjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbCgjbDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbCgjb> implements FxJkcbCgjbDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyFxJkcbCgjb> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zzyflId,sum(ndjh) as ndjh, sum(ydjh) as ydjh, sum(ydwc) as ydwc from JygkZzyFxJkcbCgjb where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf group by zzyflId");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyFxJkcbCgjb> retList=new ArrayList<JygkZzyFxJkcbCgjb>();
		for(Object[] oa:objectList){
			JygkZzyFxJkcbCgjb j=new JygkZzyFxJkcbCgjb();
			j.setZzyflId((int)oa[0]);
			j.setNdjh((BigDecimal)oa[1]);
			j.setYdjh((BigDecimal)oa[2]);
			j.setYdwc((BigDecimal)oa[3]);
			retList.add(j);
		}
		return retList;
	}	
}