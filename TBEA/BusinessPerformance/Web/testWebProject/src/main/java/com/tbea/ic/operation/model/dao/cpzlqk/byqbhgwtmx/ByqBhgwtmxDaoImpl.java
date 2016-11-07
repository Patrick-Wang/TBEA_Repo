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
	public List<ByqBhgwtmxEntity> getByYd(Date d,  List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        
		String sql =  "from ByqBhgwtmxEntity where nf = :nf and yf = :yf and zt in :zt";
		Query q = getEntityManager().createQuery(sql);
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("zt", zts);
		return q.getResultList();
	}

	@Override
	public List<ByqBhgwtmxEntity> getByJd(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt");
		String sql = "from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt";
		Query q = getEntityManager().createQuery(sql);

        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		return q.getResultList();
	}
	@Override
	public List<Object[]> getByYdFb(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        // Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where nf = :nf and yf = :yf  and zt=:zt group by dwid, bhglb.id order by dwid");
        String sql = sql = "select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where nf = :nf and yf = :yf  and zt in :zt group by dwid, bhglb.id order by dwid";
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}
	
	@Override
	public List<Object[]> getByYdFbHj(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, count(*) from ByqBhgwtmxEntity where nf = :nf and yf = :yf  and zt=:zt group by dwid order by dwid");
        String sql = "select dwid, count(*) from ByqBhgwtmxEntity where nf = :nf and yf = :yf  and zt in :zt group by dwid order by dwid";
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getByJdFb(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt group by dwid, bhglb.id order by dwid");
        String sql = "select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt group by dwid, bhglb.id order by dwid";
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getByJdFbHj(Date d, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, count(*) from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt group by dwid order by dwid");
        String sql = "select dwid, count(*) from ByqBhgwtmxEntity where nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt group by dwid order by dwid";
		
		Query q = getEntityManager().createQuery(sql);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}

	
	@Override
	public ByqBhgwtmxEntity getFirstBhgwtmx(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where nf = :nf and  yf = :yf and dwid = :dwid");
        

        q.setFirstResult(0);
        q.setMaxResults(1);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		List<ByqBhgwtmxEntity> result = q.getResultList();
		if (result.isEmpty()){
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ByqBhgwtmxEntity> getByDate(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where nf = :nf and  yf = :yf and dwid = :dwid");
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}

	@Override
	public List<ByqBhgwtmxEntity> getByYd(Date d,  Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
       // Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where dwid=:dwid and nf = :nf and yf = :yf and zt=:zt");
        String sql = "from ByqBhgwtmxEntity where dwid=:dwid and nf = :nf and yf = :yf and zt in :zt";
		Query q = getEntityManager().createQuery(sql);
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		q.setParameter("zt", zts);
		return q.getResultList();
	}

	@Override
	public List<ByqBhgwtmxEntity> getByJd(Date d,  Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("from ByqBhgwtmxEntity where dwid=:dwid and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt");
        String sql = "from ByqBhgwtmxEntity where dwid=:dwid and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt";
		Query q = getEntityManager().createQuery(sql);
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		return q.getResultList();
	}

	@Override
	public List<Object[]> getByYdFb(Date d,  Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and yf = :yf  and zt=:zt group by dwid, bhglb.id order by dwid");
        String sql = "select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and yf = :yf  and zt in :zt group by dwid, bhglb.id order by dwid";
		Query q = getEntityManager().createQuery(sql);

        
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}
	
	@Override
	public List<Object[]> getByYdFbHj(Date d,  Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and yf = :yf  and zt=:zt group by dwid order by dwid");
        String sql = "select dwid, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and yf = :yf  and zt in :zt group by dwid order by dwid";

		Query q = getEntityManager().createQuery(sql);
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getByJdFb(Date d,  Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt group by dwid, bhglb.id order by dwid");
        String sql = "select dwid, bhglb.id, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt group by dwid, bhglb.id order by dwid";
		Query q = getEntityManager().createQuery(sql);
        
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}
	
	@Override
	public List<Object[]> getByJdFbHj(Date d, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
        //Query q = getEntityManager().createQuery("select dwid, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt=:zt group by dwid order by dwid");
        String sql ="select dwid, count(*) from ByqBhgwtmxEntity where dwid=:dwid  and  nf = :nf and  yf <= :yf and yf >= :jdstart and zt in :zt group by dwid order by dwid";
		Query q = getEntityManager().createQuery(sql);
        
        q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		Integer jdStart = 1;
        if (ec.getSeason() == 3){
        	jdStart = 7;
        }
        
		q.setParameter("jdstart", jdStart);
		q.setParameter("zt", zts);
		List<Object[]> result = q.getResultList();
		return result;
	}

}
