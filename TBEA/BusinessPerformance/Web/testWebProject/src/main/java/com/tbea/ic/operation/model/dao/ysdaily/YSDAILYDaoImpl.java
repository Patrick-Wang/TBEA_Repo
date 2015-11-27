package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;
import com.tbea.ic.operation.model.entity.jygk.YSDAILYPK;

@Repository
@Transactional("transactionManager")
public class YSDAILYDaoImpl implements YSDAILYDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;

	@Override
	public YSDAILY getYsdaily(Date date, DWXX dwxx) {
		YSDAILYPK ysdaily = new YSDAILYPK();
		ysdaily.setDwxx(dwxx);
		ysdaily.setDate(date);
		Query q = entityManager.createQuery("from YSDAILY where key = :key");
		q.setParameter("key", ysdaily);
		List<YSDAILY> yspks = q.getResultList();
		if (!yspks.isEmpty()){
			return (YSDAILY)yspks.get(0);
		}
		return null;
	}

	@Override
	public void update(YSDAILY daily) {
		entityManager.merge(daily);		
	}

	@Override
	public Double getJzydyszkzmye(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalFundsTargetMonth) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getZqbc(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalPlan) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getQbbc(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalToday) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getYhkzkjyshkje(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(kjysWithdrawalToday) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getJrhk(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(qbbcMoney) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getGdwzxzdhkjh(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(zqbcMoney) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getJtxdydzjhlzb(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(balanceAccount) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

}
