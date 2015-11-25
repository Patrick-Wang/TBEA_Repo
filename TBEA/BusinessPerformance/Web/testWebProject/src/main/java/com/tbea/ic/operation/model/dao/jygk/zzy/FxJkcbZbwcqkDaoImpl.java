package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZbwcqk;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbZbwcqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbZbwcqk> implements FxJkcbZbwcqkDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyFxJkcbZbwcqk> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zzyflId,sum(ndjh) as ndjh, sum(ydjh) as ydjh, sum(ydwc) as ydwc from JygkZzyFxJkcbZbwcqk where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf  group by zzyflId");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}	
}
