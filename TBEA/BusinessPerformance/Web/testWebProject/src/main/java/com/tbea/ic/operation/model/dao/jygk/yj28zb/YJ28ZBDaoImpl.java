package com.tbea.ic.operation.model.dao.jygk.yj28zb;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class YJ28ZBDaoImpl extends AbstractReadWriteDaoImpl<YJ28ZB> implements YJ28ZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public YJ28ZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<YJ28ZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<YJ28ZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

//	@Override
//	public List<YJ28ZB> getApprovedZbs(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 1 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return q.getResultList();
//	}
//
//	@Override
//	public List<YJ28ZB> getUnapprovedZbs(Date date, List<Company> comps) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 2 and dwxx.id in ("+ Util.toBMString(comps) +")");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		return q.getResultList();
//	}

	@Override
	public List<YJ28ZB> getZb(List<Company> comps, Date dStart, Date dEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("dStart",dStart);
		q.setParameter("dEnd", dEnd);
		return q.getResultList();
	}

	@Override
	public List<YJ28ZB> getZb(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

//	@Override
//	public List<YJ28ZB> getApprovedZbs(Date date, List<Company> comps) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 1 and dwxx.id in ("+ Util.toBMString(comps) +")");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		return q.getResultList();
//	}
	
	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from YJ28ZB group by dwxx.id");
		return q.getResultList();
	}

	@Override
	public List<YJ28ZB> getYj28zbs(List<YDZBZT> yd28zbzts, List<Integer> zbs) {
		String strExeSQL = "from YJ28ZB where (";
		for(int i = 0; i < yd28zbzts.size(); i++)
		{
			strExeSQL += "(nf = " + yd28zbzts.get(i).getNf() + " and yf = " + yd28zbzts.get(i).getYf() + " and dwxx.id =" + yd28zbzts.get(i).getDwxx().getId() + ") or";
		}
		strExeSQL = strExeSQL.substring(0, strExeSQL.length() - 2) + ") and  zbxx.id in (" + Util.toInteger(zbs) + ")";
		Query q = this.getEntityManager().createQuery(strExeSQL);
		return q.getResultList();
	}

//	@Override
//	public int getApprovedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 1 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	@Override
	public List<Object[]> getEntryCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id, yj28shzt.name from YJ28ZB where nf = :nf and yf = :yf group by dwxx.id, yj28shzt.name");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public Timestamp getEntryTime(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and dwxx.id = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YJ28ZB> ret = q.getResultList();
		if (!ret.isEmpty()){
			return ret.get(0).getYj28xgsj();
		}
		return null;
	}

//	@Override
//	public int getSavedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 3 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	@Override
	public ZBStatus getZbStatus(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select yj28shzt.id from YJ28ZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<Object> ret = q.getResultList();
		if (ret.isEmpty()){
			return ZBStatus.NONE;
		}
		return ZBStatus.valueOf(((Integer)ret.get(0)));
	}

	@Override
	public List<YJ28ZB> getZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<Integer> getApprovedCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id from YJ28ZB where nf = :nf and yf = :yf and yj28shzt.id = 1 group by dwxx.id");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public Timestamp getApprovedTime(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ28ZB where nf = :nf and yf = :yf and dwxx.id = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YJ28ZB> ret = q.getResultList();
		if (!ret.isEmpty()){
			return ret.get(0).getYj28shsj();
		}
		return null;
	}
	
	@Override
	public Double getZb(Integer zbId, Date date, List<Integer> companies) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select sum(yj28z) from YJ28ZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id in :comp");
		q.setParameter("id", zbId);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", companies);
		List<Double> ret = q.getResultList();
		return ret.get(0);
	}
}
