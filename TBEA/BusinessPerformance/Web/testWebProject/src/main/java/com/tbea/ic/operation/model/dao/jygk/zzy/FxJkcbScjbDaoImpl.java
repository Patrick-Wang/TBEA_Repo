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
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbScjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbScjbDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbScjb> implements FxJkcbScjbDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//单个指标数据
	public JygkZzyFxJkcbScjb getZb(int zb, Date date, int company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from JygkZzyFxJkcbScjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company);
		List<JygkZzyFxJkcbScjb> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
}
