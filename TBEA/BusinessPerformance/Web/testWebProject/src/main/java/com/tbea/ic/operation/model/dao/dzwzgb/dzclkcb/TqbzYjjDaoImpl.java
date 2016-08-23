package com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.model.entity.dzwzgb.TqbzYjjEntity;



@Repository(TqbzYjjDaoImpl.NAME)
@Transactional("transactionManager2")
public class TqbzYjjDaoImpl implements TqbzYjjDao {
	public final static String NAME = "TqbzYjjDaoImpl";

	@PersistenceContext(unitName = "15DB")
	EntityManager entityManager;

	@Override
	public List<TqbzYjjEntity> getByDate(Date d) {
		EasyCalendar cal = new EasyCalendar(d);
		Query q = entityManager.createQuery("from TqbzYjjEntity where YF=:yf");
		q.setParameter("yf", Double.valueOf(String.format("%4d%02d", cal.getYear(), cal.getMonth())));
		return q.getResultList();
	}

	@Override
	public List<Object[]> getRsByDate(Date d) {
		EasyCalendar ec = new EasyCalendar(d);
		String sql = "select * from t_jysj2014_hrrs where " + 
		"HR_unitcode in('10100','10200','10300','10500','10600','10700','11000','11200','10800','10900','11100','11300','10400') and NF = :nf and YF = :yf ";
		
		Query q = entityManager.createNativeQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		return q.getResultList();
	}
}
