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
	public List<JygkZzyFxJkcbZbwcqk> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbZbwcqk where dwid = :dwid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxJkcbZbwcqk readDataByDwFlData(int dwxxId,int fl,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbZbwcqk where dwid = :dwid and zzyflId = :fl and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("fl", fl);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<JygkZzyFxJkcbZbwcqk> jygkZzyFxJkcbZbwcqkList = q.getResultList();
		if (!jygkZzyFxJkcbZbwcqkList.isEmpty()){
			return jygkZzyFxJkcbZbwcqkList.get(0);
		}
		return null;
	}
}
