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
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
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
	public List<ByqBhgwtmxEntity> getByYd(Date d, int tjfs, ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where tjfs = :tjfs and nf = :nf and yf = :yf and zt=:zt");
        q.setParameter("tjfs", tjfs);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("zt", zt.ordinal());
		return q.getResultList();
	}

	@Override
	public List<ByqBhgwtmxEntity> getByJd(Date d, int tjfs, ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		q.setParameter("zt", zt.ordinal());
		return q.getResultList();
	}
	@Override
	public List<Object[]> getByYdFb(Date d, int tjfs, ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and yf = :yf  and zt=:zt group by dwid, bhglb.id order by dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("zt", zt.ordinal());
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getByJdFb(Date d, int tjfs, ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where tjfs = :tjfs and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt group by dwid, bhglb.id order by dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		q.setParameter("zt", zt.ordinal());
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public ByqBhgwtmxEntity getFirstBhgwtmx(Date d, Company company, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where tjfs = :tjfs and nf = :nf and  yf = :yf and dwid = :dwid");
        q.setFirstResult(0);
        q.setMaxResults(1);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("dwid", company.getId());
		List<ByqBhgwtmxEntity> result = q.getResultList();
		if (result.isEmpty()){
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ByqBhgwtmxEntity> getByDate(Date d, Company company, int tjfs) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where  tjfs = :tjfs and nf = :nf and  yf = :yf and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("tjfs", tjfs);
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}

}
