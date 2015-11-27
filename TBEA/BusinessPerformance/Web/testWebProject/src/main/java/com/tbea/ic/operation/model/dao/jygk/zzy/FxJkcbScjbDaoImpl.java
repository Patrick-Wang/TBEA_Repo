package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbScjb;
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
	public JygkZzyFxJkcbScjb getViewData(int zbxxId, Date date, String dwxxs) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String sql = "select sum(sjlyl),sum(fl) from JygkZzyFxJkcbScjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid in (";
		sql += dwxxs;
		sql += ") group by zzyfl_id";
		
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("id", zbxxId);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		List<Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			Object[] obj = zbs.get(0);
			JygkZzyFxJkcbScjb o = new JygkZzyFxJkcbScjb();
			o.setSjlyl((BigDecimal)obj[0]);
			o.setFl((BigDecimal)obj[1]);
			return o;
		}
		return null;
	}
}
