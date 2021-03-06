package com.tbea.ic.operation.model.dao.jygk.sjzb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
@Repository
@Transactional("transactionManager")
public class SJZBDaoImpl extends AbstractReadWriteDaoImpl<SJZB> implements SJZBDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public SJZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<SJZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<SJZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<SJZB> getZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id in (" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

//	@Override
//	public List<SJZB> getUnapprovedZbs(Date date, List<Company> comps) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and sjshzt.id = 2 and dwxx.id in (" + Util.toString(comps) + ")");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		return q.getResultList();
//	}
//
//	@Override
//	public List<SJZB> getApprovedZbs(Date date, List<Company> comps) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and sjshzt.id = 1 and dwxx.id in (" + Util.toString(comps) + ")");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		return q.getResultList();
//	}

	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from SJZB group by dwxx.id");
		return q.getResultList();
	}
	
	@Override
	public Double[] GetMonthActualValue(List<Company> comps, Date date, List<Integer> indexlist)
	{
		
		Map<Integer, Integer> hyMap = new HashMap<Integer, Integer>();
		for(int iSize = 0; iSize < indexlist.size(); iSize++)
		{
			hyMap.put(indexlist.get(iSize), iSize);
		}
		
		Double[] dValue = new Double[indexlist.size()];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createNativeQuery("select dwid, zt FROM jygk_ydzbzt where nf = :nf and yf = :yf and dwid in(" + Util.toString(comps) + ")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		
		List<Object[]> listStatusValue = q.getResultList();
		List<Integer> listcomps20 = null;
		List<Integer> listcomps28 = null;
		List<Integer> listcompsActual = null;
		for(int iSize = 0; iSize < listStatusValue.size(); iSize++)
		{
			Integer temp  = (Integer)(listStatusValue.get(iSize)[1]);
			switch(temp)
			{
			case 1: 
				listcomps20.add((Integer)(listStatusValue.get(iSize)[0]));
				break;
			case 2:
				listcomps28.add((Integer)(listStatusValue.get(iSize)[0]));
				break;
			case 3:
				listcompsActual.add((Integer)(listStatusValue.get(iSize)[0]));
				break;
			default:
				break;
			}
		}
		if(!listcomps20.isEmpty())
		{
			Query q1 = this.getEntityManager().createNativeQuery("select zbid, sum(ndjhz) FROM jygk_yj20zb where dwid in(" + Util.toInteger(listcomps20) + ") and "
					+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf and yf = :yf group by zbid order by zbid ;");
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
			List<Object[]> listcomps20Value  = q1.getResultList();
			for(int i = 0; i < listcomps20Value.size(); i++)
			{
				dValue[hyMap.get(((Integer)(listcomps20Value.get(i)[0])))] = (double)(listcomps20Value.get(i)[1]);
				//dValue[((Integer)(listcomps20Value.get(i)[0])) - 1] += (Integer)(listcomps20Value.get(i)[2]);
			}
			
		}
		if(!listcomps28.isEmpty())
		{
			Query q2 = this.getEntityManager().createNativeQuery("select zbid, sum(ndjhz) FROM jygk_yj28zb where dwid in(" + Util.toInteger(listcomps28) + ") and "
					+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf and yf = :yf group by zbid order by zbid ;");
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
			List<Object[]> listcomps28Value  = q2.getResultList();;
			for(int i = 0; i < listcomps28Value.size(); i++)
			{
				dValue[hyMap.get(((Integer)(listcomps28Value.get(i)[0])))] = (double)(listcomps28Value.get(i)[1]);
				//dValue[((Integer)(listcomps20Value.get(i)[0])) - 1] += (Integer)(listcomps20Value.get(i)[2]);
			}
		}
		if (!listcompsActual.isEmpty())
		{
			Query q3 = this.getEntityManager().createNativeQuery("select zbid, sum(ndjhz) FROM jygk_sjzb where dwid in(" + Util.toInteger(listcompsActual) + ") and "
					+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf and yf = :yf group by zbid order by zbid ;");
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
			List<Object[]> listcompsActualValue  = q3.getResultList();
			for(int i = 0; i < listcompsActualValue.size(); i++)
			{
				dValue[hyMap.get(((Integer)(listcompsActualValue.get(i)[0])))] = (double)(listcompsActualValue.get(i)[1]);
				//dValue[((Integer)(listcomps20Value.get(i)[0])) - 1] += (Integer)(listcomps20Value.get(i)[2]);
			}
		}
//		double dPersonAverage = 0.0;
//		double dPersonIncome = 0.0;
//		double dThreeRate = 0.0;
		for(int iSize = 0; iSize < indexlist.size(); iSize++)
		{
			if( 62 == (Integer)(indexlist.get(iSize)))
			{
				dValue[hyMap.get(62)] = dValue[hyMap.get(1)]/dValue[hyMap.get(61)];
			}
			
			if( 63 == (Integer)(indexlist.get(iSize)))
			{
				dValue[hyMap.get(63)] = dValue[hyMap.get(6)]/dValue[hyMap.get(61)];
			}
			
			if( 65 == (Integer)(indexlist.get(iSize)))
			{
				dValue[hyMap.get(62)] = dValue[hyMap.get(64)]/dValue[hyMap.get(6)];
			}
		}
		
		return dValue;
	}
	
	
	@Override
	public Double[] GetSeasonSumActualValue(List<Company> comps, Date date, List<Integer> indexlist)
	{
		Double[] dValue = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(1 == (cal.get(Calendar.MONTH) + 1) || 4 == (cal.get(Calendar.MONTH) + 1) || 7 == (cal.get(Calendar.MONTH) + 1) || 10 == (cal.get(Calendar.MONTH) + 1))
		{
			dValue = GetMonthActualValue(comps, date, indexlist);
		}else if (2 == (cal.get(Calendar.MONTH) + 1) || 5 == (cal.get(Calendar.MONTH) + 1) || 8 == (cal.get(Calendar.MONTH) + 1) || 11 == (cal.get(Calendar.MONTH) + 1))
		{
			int beginMonth = cal.get(Calendar.MONTH);
			int endMonth = cal.get(Calendar.MONTH) + 1;
			//dValue = GetMonthsActualValue(comps, date, beginMonth, endMonth, indexlist);
		}else {
			int beginMonth = cal.get(Calendar.MONTH) - 1;
			int endMonth = cal.get(Calendar.MONTH) + 1;
			//dValue = GetMonthsActualValue(comps, date, beginMonth, endMonth, indexlist);
		}
		return dValue;
	}
	
	
	private Double[] GetMonthsActualValue(List<Company> comps, int complevel, Date date, int beginMonth, int endMonth, List<Integer> indexlist)
	{
		Map<Integer, Integer> hyMap = new HashMap<Integer, Integer>();
		for(int iSize = 0; iSize < indexlist.size(); iSize++)
		{
			hyMap.put(indexlist.get(iSize), iSize);
		}
		Double[] dMonthsValue = null;
		Double[] dPreMonthValue = null;
		//GetMonthActualValue();
		if(1 == (endMonth - beginMonth))
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Query q = this.getEntityManager().createNativeQuery("select zbid, sum(sjz) FROM jygk_sjzb where dwid in(" + Util.toString(comps) + ") and "
					+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf and yf = :yf group by zbid order by zbid ;");
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", beginMonth);
			List<Object[]> ListSeasonValue= q.getResultList();
			for(int i = 0; i < ListSeasonValue.size(); i++)
			{
				dPreMonthValue[hyMap.get(((Integer)(ListSeasonValue.get(i)[0])))] = (double)(ListSeasonValue.get(i)[1]);
			}
			Double[] curMonthValue = GetMonthActualValue(comps, date, indexlist);
			List<Object[]> ListSBDSpecicalIndex = null;
			if(1 == complevel || 2 == complevel)
			{
				
			}
			
			for(int iSize = 0; iSize < indexlist.size(); iSize++)
			{
				if((32 != (Integer)(indexlist.get(iSize))) 
					&& (35 != (Integer)(indexlist.get(iSize))) 
					&& (62 != (Integer)(indexlist.get(iSize)))
					&&(63 != (Integer)(indexlist.get(iSize)))
					&& (65!= (Integer)(indexlist.get(iSize))))
				{
					dMonthsValue[iSize] = dPreMonthValue[iSize] + curMonthValue[iSize];
				}
				
				if(32 == (Integer)(indexlist.get(iSize)))
				{
				}
			}
			
			
		}else if (2 == (endMonth - beginMonth))
		{
			
		}
		return null;
		
	}
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	public Double[] GetSBDCurMonthPlanSpecialIndex(List<Company> complist, Date d, List<Integer> indexlist)
	{
		
		Double[] listSpecialIndex = null;
		List<Company> listComps = new ArrayList<Company>();
		List<Integer> listSBDSpecialIndex = null;
		listSBDSpecialIndex.add(32);
		listSBDSpecialIndex.add(35);
		//listSpecialIndex = GetMonthActualValue(listCompsNum, d, listSBDSpecialIndex);
		return listSpecialIndex;
	}

	@Override
	public Double[] GetSeasonSumValue(List<Company> comps, Date date,
			List<Integer> indexlist) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<Object[]> GetYearSumValue(List<Company> comps, Date date, List<Integer> indexlist)
//	{
//		Double[] dYearVal= null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createNativeQuery("select zbid, sum(ndjhz) FROM jygk_ndjhzb where dwid in(" + Util.toString(comps) + ") and "
//				+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf group by zbid order by zbid ;");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		List<Object[]> listYearPlanValue = q.getResultList();
//		return listYearPlanValue;
//	}

	
	@Override
	public List<Object[]> GetMonthPlanValue(List<Company> comps, Date date, List<Integer> indexlist)
	{
		Double[] dYearVal= null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createNativeQuery("select zbid, sum(ydjhz) FROM jygk_ydjhzb where dwid in(" + Util.toString(comps) + ") and "
				+ "zbid in(" + Util.toInteger(indexlist) + ") and nf = :nf and yf = :yf group by zbid order by zbid ;");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH));
		List<Object[]> listYearPlanValue = q.getResultList();

		return listYearPlanValue;
	}
	
//	@Override
//	public List<Object[]> GetSBDYSPlanValue(List<Company> comps, Date date, List<Integer> indexlist)
//	{
//		Double[] dSBDYSPlanVal= null;
//		Calendar cal = Calendar.getInstance();
//		if (1 == (cal.get(Calendar.MONTH) + 1))
//		{
//			dSBDYSPlanVal = GetMonthActualValue(comps, date, indexlist);
//		}
//		else
//		{
//			GetMonthsActualValue()
//		}
//		Query q = this.getEntityManager().createNativeQuery("select zbid, sum(sjz) FROM jygk_sjzb where dwid in(" + Util.toString(comps) + ") and "
//				+ "zbid = :zbid and nf = :nf and yf = :yf group by zbid order by zbid ;");
//	}

	@Override
	public List<SJZB> getSjzbs(List<YDZBZT> sjzbzts, List<Integer> zbs) {
		String strExeSQL = "from SJZB where (";
		for(int i = 0; i < sjzbzts.size(); i++)
		{
			strExeSQL += "(nf = " + sjzbzts.get(i).getNf() + " and yf = " + sjzbzts.get(i).getYf() + " and dwxx.id =" + sjzbzts.get(i).getDwxx().getId() + ") or";
		}
		strExeSQL = strExeSQL.substring(0, strExeSQL.length() - 2) + ") and  zbxx.id in (" + Util.toInteger(zbs) + ")";
		Query q = this.getEntityManager().createQuery(strExeSQL);
		return q.getResultList();
	}

//	@Override
//	public int getApprovedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from SJZB where nf = :nf and yf = :yf and sjshzt.id = 1 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	
	@Override
	public List<Object[]> getEntryCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id, sjshzt.name from SJZB where nf = :nf and yf = :yf group by dwxx.id, sjshzt.name");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public Timestamp getEntryTime(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<SJZB> ret = q.getResultList();
		if (!ret.isEmpty()){
			return ret.get(0).getSjxgsj();
		}
		return null;
	}

//	@Override
//	public int getSavedZbsCount(Date date, Company company) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		Query q = this.getEntityManager().createQuery("select count(*) from SJZB where nf = :nf and yf = :yf and sjshzt.id = 3 and  dwxx.id = :comp");
//		q.setParameter("nf", cal.get(Calendar.YEAR));
//		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
//		q.setParameter("comp", company.getId());
//		return ((Long)q.getSingleResult()).intValue();
//	}

	@Override
	public ZBStatus getZbStatus(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select sjshzt.id from SJZB where nf = :nf and yf = :yf and dwxx.id = :comp");
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
	public List<Integer> getApprovedCompletedCompanies(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("select dwxx.id from SJZB where nf = :nf and yf = :yf and sjshzt.id = 1 group by dwxx.id");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public Timestamp getApprovedTime(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from SJZB where nf = :nf and yf = :yf and dwxx.id = :compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<SJZB> ret = q.getResultList();
		if (!ret.isEmpty()){
			return ret.get(0).getSjshsj();
		}
		return null;
	}
	
}
