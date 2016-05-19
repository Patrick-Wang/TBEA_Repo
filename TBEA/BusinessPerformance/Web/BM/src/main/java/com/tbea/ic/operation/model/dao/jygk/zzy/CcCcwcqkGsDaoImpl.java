package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcCcwcqkGs;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class CcCcwcqkGsDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyCcCcwcqkGs> implements CcCcwcqkGsDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public Object[] getViewData(Integer zbxxId, Date date, String dwxxs) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String sql = "select sum(gs),sum(gs) as e from JygkZzyCcCcwcqkGs where zzyfl_id = :id and nf = :nf and yf = :yf and dwid in (";
		sql += dwxxs;
		sql += ") group by zzyfl_id";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("id", zbxxId);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
}
