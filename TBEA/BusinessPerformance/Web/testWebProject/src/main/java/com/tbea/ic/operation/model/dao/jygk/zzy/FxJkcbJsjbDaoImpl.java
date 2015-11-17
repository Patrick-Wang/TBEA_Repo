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

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbJsjbDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbJsjb> implements FxJkcbJsjbDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//单个指标数据
	public JygkZzyFxJkcbJsjb getZb(Integer zb, Date date, int company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("JygkZzyFxJkcbScjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company);
		List<JygkZzyFxJkcbJsjb> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}


	@Override
	public List<JygkZzyFxJkcbJsjb> getDataListByDwDate(int dwxxId,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbJsjb where dwid = :dwid and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);		
		return q.getResultList();
	}
	@Override
	public JygkZzyFxJkcbJsjb readDataByDwFlData(int dwxxId,int fl,int nf,int yf) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbJsjb where dwid = :dwid and zzyflId = :fl and nf = :nf and yf = :yf");
		q.setParameter("dwid", dwxxId);
		q.setParameter("fl", fl);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<JygkZzyFxJkcbJsjb> jygkZzyFxJkcbJsjbList = q.getResultList();
		if (!jygkZzyFxJkcbJsjbList.isEmpty()){
			return jygkZzyFxJkcbJsjbList.get(0);
		}
		return null;
	}
}
