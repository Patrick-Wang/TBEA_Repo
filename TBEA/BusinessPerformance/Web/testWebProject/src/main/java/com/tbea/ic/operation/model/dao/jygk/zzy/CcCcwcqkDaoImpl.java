package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcCcwcqk;

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
	public List<JygkZzyCcCcwcqk> getDataListByDwDate(String dwxxs,int nf,int yf) {	
		Query q = this.getEntityManager().createQuery("select zzyflId,sum(cz) as cz, sum(cl) as cl from JygkZzyCcCcwcqk where dwid in (" + dwxxs + ") and nf = :nf and yf = :yf group by zzyflId");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<Object[]> objectList=q.getResultList();
		List<JygkZzyCcCcwcqk> retList=new ArrayList<JygkZzyCcCcwcqk>();
		for(Object[] oa:objectList){
			JygkZzyCcCcwcqk j=new JygkZzyCcCcwcqk();
			j.setZzyflId((int)oa[0]);
			j.setCz((BigDecimal)oa[1]);
			j.setCl((BigDecimal)oa[2]);
			retList.add(j);
		}
		return retList;
	}
	@Override
	public List<Object[]> getSumDataListByDwData(int dwxxId,int nf) {	
		Query q = this.getEntityManager().createQuery("select dwid,sum(cl) as cl,sum(cz) as cz from "
				+ "JygkZzyCcCcwcqk where dwid = :dwid and nf = :nf group by dwid");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		return q.getResultList();
	}
	
	@Override
	//单个指标数据
	public Object[] getViewData(Integer zb, Date date, String dwxxs) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String sql = "select sum(cl),sum(cz) from JygkZzyCcCcwcqk where zzyfl_id = :id and nf = :nf and yf = :yf and dwid in (";
		sql += dwxxs;
		sql += ") group by zzyfl_id ";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
}
