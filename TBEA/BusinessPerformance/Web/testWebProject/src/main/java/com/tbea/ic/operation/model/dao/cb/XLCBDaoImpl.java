package com.tbea.ic.operation.model.dao.cb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBXLTBDD;
import com.tbea.ic.operation.model.entity.CBXLWGDD;
import com.tbea.ic.operation.model.entity.CBXLZXDD;

@Repository
@Transactional("transactionManager")
public class XLCBDaoImpl implements XLCBDao{
	@PersistenceContext(unitName = "localDB")
	private EntityManager entityManager;

	@Override
	public List<CBXLTBDD> getTbdd(Date date) {
		Query q = entityManager.createQuery(
				"from CBXLTBDD where datediff(yyyy, tbbjsj, :date) = 0 and datediff(MM, tbbjsj, :date1) <= 0");
		q.setParameter("date", date);
		q.setParameter("date1", date);
		return q.getResultList();
	}

	@Override
	public List<CBXLZXDD> getZxdd() {
		Query q = entityManager.createQuery(
				"from CBXLZXDD");
		return q.getResultList();
	}

	@Override
	public List<CBXLWGDD> getWgdd(Date date) {
		Query q = entityManager.createQuery(
				"select w from CBXLWGDD w where w.wgsj is not null and length(w.wgsj) >= 6 and substring(w.wgsj, 1, 4) = :year and substring(w.wgsj, 5, 2) <= :month");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = "" + cal.get(Calendar.YEAR);
		q.setParameter("year", year);
		String month = "" + (cal.get(Calendar.YEAR) + 1);
		q.setParameter("month", month);
		return q.getResultList();
	}

	@Override
	public boolean containsTbCompany(Company comp) {
		Query q = entityManager
				.createQuery("SELECT t FROM CBXLTBDD t, XMXX x where t.xmbh = x.xmbh and x.ddszdw = :comp");
		q.setParameter("comp", "0" + comp.getId());
		q.setFirstResult(0);
		q.setMaxResults(1);
		return !q.getResultList().isEmpty();
	}

	@Override
	public CBXLWGDD getLatestWgdd() {
		Query q = entityManager.createQuery(
				"from CBXLWGDD order by wgsj desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<CBXLWGDD> wgdds = q.getResultList();
		if (!wgdds.isEmpty()){
			return wgdds.get(0);
		}
		return null;
	}

	@Override
	public List<Integer> getWgCompany() {
		Query q = entityManager.createQuery("SELECT w.qybh FROM CBXLWGDD w group by w.qybh");
		return q.getResultList();
	}

	@Override
	public List<Integer> getTbCompany() {
		Query q = entityManager.createQuery("SELECT t.qybh FROM CBXLTBDD t group by t.qybh");
		List<Integer> ret = q.getResultList();
		q = entityManager.createQuery("SELECT x.ddszdw FROM CBXLTBDD t, XMXX x where t.xmbh = x.xmbh group by x.ddszdw");
		ret.addAll(q.getResultList());
		return ret;
	}

	@Override
	public CBXLTBDD getLatestTbdd() {
		Query q = entityManager.createQuery(
				"from CBXLTBDD order by tbbjsj desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<CBXLTBDD> tbbjsj = q.getResultList();
		if (!tbbjsj.isEmpty()){
			return tbbjsj.get(0);
		}
		return null;
	}
}
