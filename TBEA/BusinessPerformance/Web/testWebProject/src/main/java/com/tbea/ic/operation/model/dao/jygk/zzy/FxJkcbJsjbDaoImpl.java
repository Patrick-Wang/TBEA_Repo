package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbJsjbDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbJsjb> implements FxJkcbJsjbDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<JygkZzyFxJkcbJsjb> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zzyflId, sum(cz) as cz, sum(cltdjb) as cltdjb, sum(jgcsyhjb) as jgcsyhjb, sum(qtjb) as qtjb, sum(scts) as scts, sum(yhts) as yhts from JygkZzyFxJkcbJsjb where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf  group by zzyflId");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyFxJkcbJsjb> retList=new ArrayList<JygkZzyFxJkcbJsjb>();
		for(Object[] oa:objectList){
			JygkZzyFxJkcbJsjb j=new JygkZzyFxJkcbJsjb();
			j.setZzyflId((int)oa[0]);
			j.setCz((BigDecimal)oa[1]);
			j.setCltdjb((BigDecimal)oa[2]);
			j.setJgcsyhjb((BigDecimal)oa[3]);
			j.setQtjb((BigDecimal)oa[4]);
			j.setScts((BigDecimal)oa[5]);
			j.setYhts((BigDecimal)oa[6]);
			retList.add(j);
		}
		return retList;
	}	
}
