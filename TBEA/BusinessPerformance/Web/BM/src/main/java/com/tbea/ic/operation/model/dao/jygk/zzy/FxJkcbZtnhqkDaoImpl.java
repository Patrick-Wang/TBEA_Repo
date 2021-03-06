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
	public List<Object[]> getSumDataListByDwData(String dwxxId,int nf) {	
		String sql = "select nf,sum(syl) as syl,sum(sje) as sje,sum(dyl) as dyl,sum(dje) as dje,sum(zqyl) as zqyl,sum(zqje) as zqje,"
				+ "sum(rqyl) as rqyl,sum(rqje) as rqje,sum(cz) as cz,sum(cl) as cl from JygkZzyFxJkcbZtnhqk where dwid in (" + dwxxId + ") and nf = " + nf + " group by nf";
		Query q = this.getEntityManager().createQuery(sql);
		return q.getResultList();
	}
	@Override
	public List<Object[]> getViewDataListByq(String year, String month, String dwxxid) {
		String sql = "select syl,sje,dyl,dje,zqyl,zqje,rqyl,rqje,cz,cl from JygkZzyFxJkcbZtnhqk where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in(";
		sql += dwxxid;
		sql += ")";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		return list;
	}
	@Override
	public List<Object[]> getViewDataListXl(String year, String month, String dwxxid) {
		String sql = "select syl,sje,dyl,dje,zqyl,zqje,rqyl,rqje,cz,cl from JygkZzyFxJkcbZtnhqk where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in(";
		sql += dwxxid;
		sql += ")";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		return list;
	}
}
