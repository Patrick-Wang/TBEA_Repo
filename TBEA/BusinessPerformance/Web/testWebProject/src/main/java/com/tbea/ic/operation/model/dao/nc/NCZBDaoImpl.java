package com.tbea.ic.operation.model.dao.nc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NCZB;

@Repository
@Transactional("transactionManager")
public class NCZBDaoImpl extends AbstractReadWriteDaoImpl<NCZB> implements
		NCZBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public NCZB getNCZB(Company company, int zbid, int nf, int yf) {
		NCZB nczb = null;
		String sql = "From NCZB Where dwxx.id = :dwid and zbxx.id = :zbid"
				+ " and nf = :nf and yf = :yf";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("dwid", company.getId());
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		try {
			nczb = (NCZB) q.getSingleResult();
		} catch (NoResultException e) {
			nczb = null;
		}
		return nczb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NCZB> getNCZBByDate(int nf, int yf) {
		String sql = "From NCZB Where nf = :nf and yf = :yf";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<NCZB> resultList = null;
		try {
			resultList = q.getResultList();
		} catch (Exception e) {
			resultList = new ArrayList<NCZB>();
		}
		return resultList;
	}

	@Override
	public List<Double> getSjzbs(Date start, Date end, List<Integer> zbsTmp,
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
		Query q = this.getEntityManager().createQuery("select zbxx.id, sum(nczbz) from NCZB where " + 
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dStart) <= 0 and " +
		"dateDiff(mm, dateadd(mm, yf - 1, dateadd(yy, nf -1900 ,'1900-1-1')), :dEnd) >= 0 and " +
		"dwxx.id in ("+ Util.toBMString(companies) +") and " + 
		"zbxx.id in (" + Util.toInteger(zbsTmp) + ") " + "group by zbxx.id");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		
		List<Object[]> listRet = q.getResultList();
		for(int i = 0; i < listRet.size(); i++)
		{
			listDyjhz.set(hyMap.get(listRet.get(i)[0]), ((Double)(listRet.get(i)[1])));
		}
		return listDyjhz;
	}

}
