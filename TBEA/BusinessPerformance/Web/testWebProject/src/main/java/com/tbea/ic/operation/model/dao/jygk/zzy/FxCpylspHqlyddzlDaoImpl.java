package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.math.BigDecimal;
import java.util.ArrayList;
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
	public List<JygkZzyFxCpylspHqlyddzl> getDataListByDwDate(String dwxxs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zzyflId,sum(cz) as cz, sum(cl) as cl, sum(yjyhhmle) as yjyhhmle, sum(yjyhhmll) as yjyhhmll, sum(zbmll) as zbmll from JygkZzyFxCpylspHqlyddzl where dwid in (" + dwxxs + ")  and nf = :nf and jd = :jd  group by zzyflId");		
		q.setParameter("nf", nf);
		q.setParameter("jd", yf);
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyFxCpylspHqlyddzl> retList=new ArrayList<JygkZzyFxCpylspHqlyddzl>();
		for(Object[] oa:objectList){
			JygkZzyFxCpylspHqlyddzl j=new JygkZzyFxCpylspHqlyddzl();
			j.setZzyflId((int)oa[0]);
			j.setCz((BigDecimal)oa[1]);
			j.setCl((BigDecimal)oa[2]);
			j.setYjyhhmle((BigDecimal)oa[3]);
			j.setYjyhhmll((BigDecimal)oa[4]);
			j.setZbmll((BigDecimal)oa[5]);
			retList.add(j);
		}
		return retList;
	}
	@Override
	public JygkZzyFxCpylspHqlyddzl readDataByDwFlDate(int dwxxId,int fl,int nf,int yf) {
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
