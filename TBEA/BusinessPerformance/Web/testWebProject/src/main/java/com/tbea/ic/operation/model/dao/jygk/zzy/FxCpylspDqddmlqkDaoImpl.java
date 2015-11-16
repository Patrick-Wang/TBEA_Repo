package com.tbea.ic.operation.model.dao.jygk.zzy;


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
	public List<JygkZzyFxCpylspDqddmlqk> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxCpylspDqddmlqk where dwid = :dwid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxCpylspDqddmlqk readDataByDwFlData(int dwxxId,int fl,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxCpylspDqddmlqk where dwid = :dwid and zzyflId = :fl and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("fl", fl);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<JygkZzyFxCpylspDqddmlqk> jygkZzyFxCpylspDqddmlqkList = q.getResultList();
		if (!jygkZzyFxCpylspDqddmlqkList.isEmpty()){
			return jygkZzyFxCpylspDqddmlqkList.get(0);
		}
		return null;
	}
}
