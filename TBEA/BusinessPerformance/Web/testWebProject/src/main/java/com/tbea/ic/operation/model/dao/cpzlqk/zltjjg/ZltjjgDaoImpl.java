package com.tbea.ic.operation.model.dao.cpzlqk.zltjjg;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;



@Repository(ZltjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class ZltjjgDaoImpl extends AbstractReadWriteDaoImpl<ZltjjgEntity> implements ZltjjgDao {
	public final static String NAME = "ZltjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public ZltjjgEntity getByDate(Date d, int cpid, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        
        Query q = getEntityManager().createQuery("from ZltjjgEntity where nf = :nf and yf = :yf and cpid = :cpid and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
		List<ZltjjgEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}

	@Override
	public ZltjjgEntity getYearAcc(Date d, int cpid, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf and cpid = :cpid and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		entity.setZs(((Long)ret.get(0)[1]).intValue());
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAcc(Date d, int cpid, Company company) {
        EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		entity.setZs(((Long)ret.get(0)[1]).intValue());
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
		ec.addYear(-1);
        Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("jdstart", ec.getCurrentSeasonFirstMonth());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		entity.setZs(((Long)ret.get(0)[1]).intValue());
		return entity;
	}
}
