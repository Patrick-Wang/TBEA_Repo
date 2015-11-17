package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZtnhqk;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbZtnhqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbZtnhqk> implements FxJkcbZtnhqkDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<JygkZzyFxJkcbZtnhqk> getDataListByDwData(int dwxxId,int nf,int yf) {	
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbZtnhqk where dwid = :dwid and nf = :nf and jd = :jd order by zzyfl_id");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("jd", yf);	
		return q.getResultList();
	}
	@Override
	public List<Object[]> getSumDataListByDwData(int dwxxId,int nf) {	
		String sql = "select dwid,sum(syl) as syl,sum(sje) as sje,sum(dyl) as dyl,sum(dje) as dje,sum(zqyl) as zqyl,sum(zqje) as zqje,"
				+ "sum(rqyl) as rqyl,sum(rqje) as rqje from JygkZzyFxJkcbZtnhqk where dwid = :dwid and nf = :nf group by dwid";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		return q.getResultList();
	}
}
