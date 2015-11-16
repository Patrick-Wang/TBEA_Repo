package com.tbea.ic.operation.model.dao.jygk.zzy;


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
	public List<JygkZzyFxJkcbCgjb> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbCgjb where dwid = :dwid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxJkcbCgjb readDataByDwFlData(int dwxxId,int fl,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbCgjb where dwid = :dwid and zzyflId = :fl and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("fl", fl);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<JygkZzyFxJkcbCgjb> jygkZzyFxJkcbCgjbList = q.getResultList();
		if (!jygkZzyFxJkcbCgjbList.isEmpty()){
			return jygkZzyFxJkcbCgjbList.get(0);
		}
		return null;
	}
}