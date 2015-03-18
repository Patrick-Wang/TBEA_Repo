package com.tbea.ic.operation.model.dao.jygk.qnjh;

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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class NDJHZBDaoImpl extends AbstractReadWriteDaoImpl<NDJHZB> implements NDJHZBDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public NDJHZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where zbxx.id = :id and nf = :nf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("comp", company.getId());
		List<NDJHZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<NDJHZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where nf = :nf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<NDJHZB> getZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where nf = :nf and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		return q.getResultList();
	}

	@Override
	public List<NDJHZB> getUnapprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where nf = :nf and ndjhshzt.id = :id and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("id", 2);
		return q.getResultList();
	}

	@Override
	public List<NDJHZB> getApprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from NDJHZB where nf = :nf and ndjhshzt.id = :id and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("id", 1);
		return q.getResultList();
	}
	
	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from NDJHZB");
		return q.getResultList();
	}
	
	//All Year Plan
	@Override
	public List<Double> getQnjhz(Date date, List<Integer> zbIds,
			List<Company> companies) {
		List<Double> listYearPlanValue = new ArrayList<Double>();
		Map<Integer, Integer> hyMap = new HashMap<Integer, Integer>();
		for(int iSize = 0; iSize < zbIds.size(); iSize++)
		{
			hyMap.put(zbIds.get(iSize), iSize);
			listYearPlanValue.add(null);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createNativeQuery("select zbid, sum(ndjhz) FROM jygk_ndjhzb where dwid in(" + Util.toString(companies) + ") and "
				+ "zbid in(" + Util.toInteger(zbIds) + ") and nf = :nf group by zbid;");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		List<Object[]> listRet = q.getResultList();
		for(int i = 0; i < listRet.size(); i++)
		{
			if(null == listRet.get(i)[1])
			{
				listYearPlanValue.set(hyMap.get(listRet.get(i)[0]), (Double)(listRet.get(i)[1]));
			}
			else
			{
				listYearPlanValue.set(hyMap.get(listRet.get(i)[0]), ((BigDecimal)(listRet.get(i)[1])).doubleValue());
			}
		}
		return listYearPlanValue;
	}

	@Override
	public int getApprovedZbsCount(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select count(*) from NDJHZB where nf = :nf and ndjhshzt.id = 1 and  dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("comp", company.getId());
		return ((Long)q.getSingleResult()).intValue();
	}

	@Override
	public List<Integer> getEntryCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id from NDJHZB where nf = :nf and yf = :yf group by dwxx.id");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}
}
