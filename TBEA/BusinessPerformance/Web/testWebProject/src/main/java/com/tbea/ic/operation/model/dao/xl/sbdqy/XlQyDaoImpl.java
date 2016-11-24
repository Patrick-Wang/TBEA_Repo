package com.tbea.ic.operation.model.dao.xl.sbdqy;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;

@Repository
@Transactional("transXl")
public class XlQyDaoImpl implements XlQyDao {


	@PersistenceContext(unitName = "XlDB")
	EntityManager entityManager;

	@Override
	public List<Object[]> getCpqy(Date d) {
		EasyCalendar cal = new EasyCalendar(d);
		Query q = entityManager
				.createNativeQuery(
						"select product_type, contract_volume from segment_product_contract where year = :year and month = :month");
		q.setParameter("year", cal.getYear());
		q.setParameter("month", cal.getMonth());
		return q.getResultList();
	}

	@Override
	public List<Object[]> getScqy(Date d) {
		EasyCalendar cal = new EasyCalendar(d);
		Query q = entityManager
				.createNativeQuery(
						"select industry, contract_volume from segment_market_contract where year = :year and month = :month");
		q.setParameter("year", cal.getYear());
		q.setParameter("month", cal.getMonth());
		return q.getResultList();
	}

//	@Override
//	public List<BLHTDQQKHZ> getBltbbh(Calendar cal, List<Company> comps) {
//		Calendar preYear = Calendar.getInstance();
//		preYear.set(cal.get(Calendar.YEAR) - 1, 0, 1);
//		Calendar preYearMonth = Calendar.getInstance();
//		preYearMonth.set(cal.get(Calendar.YEAR) - 1, cal.get(Calendar.MONTH),
//				cal.get(Calendar.DAY_OF_MONTH));
//		Calendar curYear = Calendar.getInstance();
//		curYear.set(cal.get(Calendar.YEAR), 0, 1);
//		Query q = getEntityManager()
//				.createQuery(
//						"select b from BLHTDQQKHZ b where b.ny >= ?1 and b.ny <= ?2 or b.ny >= ?3 and b.ny <= ?4 and b.qybh in (" + Util.toString(comps) + ")");
//		q.setParameter(1, Util.format(preYear.getTime()));
//		q.setParameter(2, Util.format(preYearMonth.getTime()));
//		q.setParameter(3, Util.format(curYear.getTime()));
//		q.setParameter(4, Util.format(cal.getTime()));
//		return q.getResultList();
//	}

}
