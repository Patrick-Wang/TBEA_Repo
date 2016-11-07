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
import com.tbea.ic.operation.common.ZBStatus;
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
	public ZltjjgEntity getByDate(Date d, int cpid, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        
		String sql = null;
		//if (zt != ZBStatus.NONE){
			sql ="from ZltjjgEntity where zt in :zt and nf = :nf and yf = :yf and cpid = :cpid and dwid = :dwid";
//		}else{
//			sql ="from ZltjjgEntity where nf = :nf and yf = :yf and cpid = :cpid and dwid = :dwid";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<ZltjjgEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}

	@Override
	public ZltjjgEntity getYearAcc(Date d, int cpid, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= 1 and yf <= :yf and cpid = :cpid and dwid = :dwid";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf and cpid = :cpid and dwid = :dwid";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAcc(Date d, int cpid, Company company, List<Integer> zts) {
        EasyCalendar ec = new EasyCalendar(d);
        String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		ec.addYear(-1);
        String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid and zt in :zt";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid = :dwid";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwid", company.getId());
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getByDateTotal(Date d, List<Integer> cplist, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		String sql = "select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf = :yf and cpid in :cpid and dwid = :dwid and zt in :zt group by dwid";

        Query q = getEntityManager().createQuery(sql);       
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cplist);
		q.setParameter("dwid", company.getId());
		q.setParameter("zt", zts);
		List<Object[]> ret = q.getResultList();
		if (ret.isEmpty() || ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getFirstTjjg(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        
        Query q = getEntityManager().createQuery("from ZltjjgEntity where nf = :nf and yf = :yf and dwid=:dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		List<ZltjjgEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}

	@Override
	public ZltjjgEntity getByDateIgnoreStatus(Date d, Integer cpid,
			Company company) {
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
	public ZltjjgEntity getByDate(Date d, int cpid, List<Integer> ids,
			List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		String sql = null;
//		if (zt != ZBStatus.NONE){
			sql = 
	        		"select sum(bhgs), sum(zs) " + 
	                		"from ZltjjgEntity where zt in :zt and nf = :nf and yf = :yf and cpid = :cpid and dwid in :dwids";
//		}else{
//			sql = 
//	        		"select sum(bhgs), sum(zs) " + 
//	                		"from ZltjjgEntity where nf = :nf and yf = :yf and cpid = :cpid and dwid in :dwids";
//		}
		Query q = getEntityManager().createQuery(sql);
       	q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwids", ids);
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		
		return entity;
	}

	@Override
	public ZltjjgEntity getYearAcc(Date d, int cpid, List<Integer> ids,
			List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);

		String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= 1 and yf <= :yf and cpid = :cpid and dwid in :dwids";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf and cpid = :cpid and dwid in :dwids";
//		}
		Query q = getEntityManager().createQuery(sql);
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwids", ids);
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
			
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAcc(Date d, int cpid, List<Integer> ids, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid in :dwids";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid in :dwids";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwids", ids);
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getJdAccQntq(Date d, int cpid, List<Integer> ids,
			List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		ec.addYear(-1);
        String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid in :dwids and zt in :zt";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and cpid = :cpid and dwid in :dwids";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpid);
		q.setParameter("dwids", ids);
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public ZltjjgEntity getByDateTotal(Date d, List<Integer> cpids,
			List<Integer> ids, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        String sql = null;
//		if (zt != ZBStatus.NONE){
			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf = :yf and cpid in :cpid and dwid in :dwids and zt in :zt";
//		}else{
//			sql ="select sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf = :yf and cpid in :cpid and dwid in :dwids";
//		}
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("cpid", cpids);
		q.setParameter("dwids", ids);
//		if (zt != ZBStatus.NONE){
			q.setParameter("zt", zts);
//		}
		List<Object[]> ret = q.getResultList();
		if (ret.isEmpty() || ret.get(0)[0] == null && ret.get(0)[1] == null){
			return null;
		}
		ZltjjgEntity entity = new ZltjjgEntity();
		if (ret.get(0)[0] != null){
			entity.setBhgs(((Long)ret.get(0)[0]).intValue());
		}
		if (ret.get(0)[1] != null){
			entity.setZs(((Long)ret.get(0)[1]).intValue());
		}
		return entity;
	}

	@Override
	public List<ZltjjgEntity> getByDateIgnoreStatus(Date d,
			Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        
        Query q = getEntityManager().createQuery("from ZltjjgEntity where nf = :nf and yf = :yf and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		List<ZltjjgEntity> ret = q.getResultList();
		return ret;
	}
}
