package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcCcwcqk;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZtnhqk;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class CcCcwcqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyCcCcwcqk> implements CcCcwcqkDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyCcCcwcqk> getDataListByDwData(int dwxxId,int nf,int yf) {	
	Query q = this.getEntityManager().createQuery("from JygkZzyCcCcwcqk where dwid = :dwid and nf = :nf and yf = :yf");
	q.setParameter("dwid", dwxxId);
	q.setParameter("nf", nf);
	q.setParameter("yf", yf);	
	return q.getResultList();
	}
	@Override
	public List<Object[]> getSumDataListByDwData(int dwxxId,int nf) {	
	Query q = this.getEntityManager().createQuery("select dwid,sum(cl) as cl,sum(cz) as cz from "
			+ "JygkZzyCcCcwcqk where dwid = :dwid and nf = :nf group by dwid");
	q.setParameter("dwid", dwxxId);
	q.setParameter("nf", nf);
	return q.getResultList();
	}
}
