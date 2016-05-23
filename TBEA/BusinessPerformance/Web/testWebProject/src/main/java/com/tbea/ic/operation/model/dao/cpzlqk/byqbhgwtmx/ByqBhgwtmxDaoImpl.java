package com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;



@Repository(ByqBhgwtmxDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqBhgwtmxDaoImpl extends AbstractReadWriteDaoImpl<ByqBhgwtmxEntity> implements ByqBhgwtmxDao {
	public final static String NAME = "ByqBhgwtmxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ByqBhgwtmxEntity> getByYd(Date d, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where tjfs = :tjfs and nf = :nf and yf = :yf");
        q.setParameter("tjfs", tjfs);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		return q.getResultList();
	}

	@Override
	public List<ByqBhgwtmxEntity> getByJd(Date d, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and  yf <= :yf and yf >= :jdstart");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		return q.getResultList();
	}
	@Override
	public List<Object[]> getByYdFb(Date d, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and yf = :yf group by dwid, bhglb.id order by dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getByJdFb(Date d, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and  yf <= :yf and yf >= :jdstart group by dwid, bhglb.id order by dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		List<Object[]> result = q.getResultList();
		return result;
	}

}
