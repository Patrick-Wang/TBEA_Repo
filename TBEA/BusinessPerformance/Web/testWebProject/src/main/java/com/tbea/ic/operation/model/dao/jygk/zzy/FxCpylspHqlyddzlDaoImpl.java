package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspHqlyddzl;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxCpylspHqlyddzlDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxCpylspHqlyddzl> implements FxCpylspHqlyddzlDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyFxCpylspHqlyddzl> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxCpylspHqlyddzl where dwid = :dwid and nf = :nf and jd = :jd");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("jd", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxCpylspHqlyddzl readDataByDwFlData(int dwxxId,int fl,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxCpylspHqlyddzl where dwid = :dwid and zzyflId = :fl and nf = :nf and jd = :jd");
		q.setParameter("dwid", dwxxId);
		q.setParameter("fl", fl);
		q.setParameter("nf", nf);
		q.setParameter("jd", yf);
		List<JygkZzyFxCpylspHqlyddzl> jygkZzyFxCpylspHqlyddzlList = q.getResultList();
		if (!jygkZzyFxCpylspHqlyddzlList.isEmpty()){
			return jygkZzyFxCpylspHqlyddzlList.get(0);
		}
		return null;
	}
}
