package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspDqddmlqk;
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
	public List<JygkZzyFxSxfykz> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxSxfykz where dwid = :dwid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxSxfykz readDataByDwFlData(int dwxxId,int zbxxid,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxSxfykz where dwid = :dwid and zbxxid = :zbxxid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("zbxxid", zbxxid);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<JygkZzyFxSxfykz> jygkZzyFxSxfykzList = q.getResultList();
		if (!jygkZzyFxSxfykzList.isEmpty()){
			return jygkZzyFxSxfykzList.get(0);
		}
		return null;
	}
}
