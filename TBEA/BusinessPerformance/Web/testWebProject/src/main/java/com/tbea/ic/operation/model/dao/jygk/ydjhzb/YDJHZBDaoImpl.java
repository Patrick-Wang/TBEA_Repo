package com.tbea.ic.operation.model.dao.jygk.ydjhzb;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class YDJHZBDaoImpl extends AbstractReadWriteDaoImpl<YDJHZB> implements YDJHZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public YDJHZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<YDJHZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<YDJHZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<YDJHZB> getZb(List<Company> comps, Date dStart, Date dEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		Query q = this.getEntityManager().createQuery("from YDJHZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("dStart",dStart);
		q.setParameter("dEnd", dEnd);
		return q.getResultList();
	}

//	@Override
//	public List<YDJHZB> getUnapprovedZbs(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp and ydjhshzt.id = 2");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return q.getResultList();
//	}

//	@Override
//	public List<YDJHZB> getApprovedZbs(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :comp and ydjhshzt.id = 1");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return q.getResultList();
//	}

	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from YDJHZB group by dwxx.id");
		return q.getResultList();
	}

	@Override
	public List<Double> getDyjhz(Date start, Date end, List<Integer> zbsTmp,
			List<Company> companies) {
		List<Double> listDyjhz = new ArrayList<Double>();
		Map<Integer, Integer> hyMap = new HashMap<Integer, Integer>();
		for(int iSize = 0; iSize < zbsTmp.size(); iSize++)
		{
			hyMap.put(zbsTmp.get(iSize), iSize);
			listDyjhz.add(null);
		}
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = this.getEntityManager().createQuery("select zbxx.id, sum(ydjhz) from YDJHZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id in ("+ Util.toBMString(companies) +") and " + 
		"zbxx.id in (" + Util.toInteger(zbsTmp) + ") " + "and ydjhshzt.id in (1,2,4) group by zbxx.id");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		
		List<Object[]> listRet = q.getResultList();
		for(int i = 0; i < listRet.size(); i++)
		{
			listDyjhz.set(hyMap.get(listRet.get(i)[0]), ((Double)(listRet.get(i)[1])));
		}
		return listDyjhz;
	}

//	@Override
//	public int getApprovedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from YDJHZB where nf = :nf and yf = :yf and ydjhshzt.id = 1 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	@Override
	public List<Integer> getEntryCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id from YDJHZB where nf = :nf and yf = :yf group by dwxx.id");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public Date getEntryTime(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YDJHZB where nf = :nf and yf = :yf and dwxx.id = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YDJHZB> ret = q.getResultList();
		if (!ret.isEmpty()){
			return ret.get(0).getYdjhxgsj();
		}
		return null;
	}

	@Override
	public List<YDJHZB> getZb(Company comp, Date dStart, Date dEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		Query q = this.getEntityManager().createQuery("from YDJHZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id = :compId");
		q.setParameter("dStart",dStart);
		q.setParameter("dEnd", dEnd);
		q.setParameter("compId", comp.getId());
		return q.getResultList();
	}

//	@Override
//	public int getSavedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from YDJHZB where nf = :nf and yf = :yf and ydjhshzt.id = 3 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	@Override
	public ZBStatus getZbStatus(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select ydjhshzt.id from YDJHZB where nf = :nf and yf = :yf and  dwxx.id = :comp");
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

}
