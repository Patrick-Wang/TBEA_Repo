package com.tbea.ic.operation.model.dao.hkjhzxqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.QYZJK;
import com.tbea.ic.operation.model.entity.YDSJHKQK;
import com.util.tools.DateUtil;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;


@Repository
@Transactional("transactionManager")
public class HKJHZXQKDaoImpl  extends AbstractReadWriteDaoImpl<QYZJK> implements HKJHZXQKDao{


	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	

	@Override
	public List<YDSJHKQK> getSjhkqk(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select h from YDSJHKQK h where h.qybh = :compId and h.ny = :date");
		q.setParameter("compId", comp.getId());
		q.setParameter("date", DateUtil.month1(d));
		return q.getResultList();
	}



//	@Override
//	public List<YDHKJHJG> getHkjhjg(Date d, Company comp) {
//		Query q = getEntityManager().createQuery("select h from YDHKJHJG h where h.qybh = :compId and h.ny = :date");
//		q.setParameter("compId", comp.getId());
//		q.setParameter("date", Util.format(d));
//		return q.getResultList();
//	}


	@Override
	public YDSJHKQK getLatestYdsjhk() {
		Query q = getEntityManager().createQuery(
				"from YDSJHKQK order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YDSJHKQK> ydhk = q.getResultList();
		if (!ydhk.isEmpty()){
			return ydhk.get(0);
		}
		return null;
	}


	@Override
	public List<YDSJHKQK> getHkqkXj(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select h from YDSJHKQK h where h.qybh = :compId and h.ny >= :dStart and h.ny <= :dEnd");
		q.setParameter("compId", comp.getId());
		Calendar cal  = Calendar.getInstance();
		cal.setTime(d);
		Date dStart = Date.valueOf(cal.get(Calendar.YEAR) + "-1-1");
		q.setParameter("dStart", DateUtil.month1(dStart));
		q.setParameter("dEnd", DateUtil.month1(d));
		return q.getResultList();
	}


	@Override
	public List<YDSJHKQK> getSjhkqk(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select h from YDSJHKQK h where h.qybh in (" + Util.toString(comps) + ") and h.ny = :date");
		q.setParameter("date", DateUtil.month1(d));
		return q.getResultList();
	}


	@Override
	public List<YDSJHKQK> getHkqkXj(Date d, List<Company> comps) {
		Query q = getEntityManager().createQuery("select h from YDSJHKQK h where h.qybh in (" + Util.toString(comps) + ") and h.ny >= :dStart and h.ny <= :dEnd");
		Calendar cal  = Calendar.getInstance();
		cal.setTime(d);
		Date dStart = Date.valueOf(cal.get(Calendar.YEAR) + "-1-1");
		q.setParameter("dStart", DateUtil.month1(dStart));
		q.setParameter("dEnd", DateUtil.month1(d));
		return q.getResultList();
	}
}
