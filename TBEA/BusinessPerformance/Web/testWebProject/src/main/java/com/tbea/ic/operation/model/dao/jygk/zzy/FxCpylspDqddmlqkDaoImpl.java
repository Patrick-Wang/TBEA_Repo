package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspDqddmlqk;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxCpylspDqddmlqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxCpylspDqddmlqk> implements FxCpylspDqddmlqkDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyFxCpylspDqddmlqk> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zzyflId,sum(sr) as sr, sum(mle) as mle from JygkZzyFxCpylspDqddmlqk where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf group by zzyflId");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyFxCpylspDqddmlqk> retList=new ArrayList<JygkZzyFxCpylspDqddmlqk>();
		for(Object[] oa:objectList){
			JygkZzyFxCpylspDqddmlqk j=new JygkZzyFxCpylspDqddmlqk();
			j.setZzyflId((int)oa[0]);
			j.setSr((BigDecimal)oa[1]);
			j.setMle((BigDecimal)oa[2]);
			retList.add(j);
		}
		return retList;
	}	
}
