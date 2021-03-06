package com.tbea.ic.operation.model.dao.cpzlqk.zltjjg;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.util.tools.MathUtil;
import com.xml.frame.report.util.EasyCalendar;



public class ZltjjgDaoCacheProxy  implements ZltjjgDao {

	ZltjjgDao stubDao;
	List<Integer> comps;
	public ZltjjgDaoCacheProxy(ZltjjgDao stubDao) {
		super();
		this.stubDao = stubDao;
		
	}
	
	public ZltjjgDaoCacheProxy setComps(List<Integer> comps){
		this.comps = comps;
		return this;
	}
	
	public ZltjjgDaoCacheProxy(ZltjjgDao stubDao, Integer comp) {
		super();
		this.stubDao = stubDao;
		this.comps = new ArrayList<Integer>();
		comps.add(comp);
	}

	@Override
	public void create(ZltjjgEntity entity) {
		stubDao.create(entity);
	}

	@Override
	public void persist(ZltjjgEntity entity) {
		stubDao.persist(entity);
	}

	@Override
	public ZltjjgEntity merge(ZltjjgEntity entity) {
		return stubDao.merge(entity);
	}

	@Override
	public void delete(ZltjjgEntity entity) {
		stubDao.delete(entity);
	}

	@Override
	public void deleteById(int id) {
		stubDao.deleteById(id);
	}

	@Override
	public Class<ZltjjgEntity> getEntityClass() {
		return stubDao.getEntityClass();
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		stubDao.setEntityManager(entityManager);
	}

	@Override
	public EntityManager getEntityManager() {
		return stubDao.getEntityManager();
	}

	@Override
	public ZltjjgEntity read(ZltjjgEntity entity) {
		return stubDao.read(entity);
	}

	@Override
	public ZltjjgEntity getById(int id) {
		return stubDao.getById(id);
	}

	@Override
	public ZltjjgEntity getFirstTjjg(Date d, Company company) {
		return stubDao.getFirstTjjg(d, company);
	}

	private List<ZltjjgEntity> byDate = null;
	private List<ZltjjgEntity> yearAcc = null;
	private List<ZltjjgEntity> jdAcc = null;
	private List<ZltjjgEntity> jdAccQntq = null;
	private List<ZltjjgEntity> dateTotal = null;
	Map<String, ZltjjgEntity> cache = null;
	private ZltjjgEntity find(List<ZltjjgEntity> zltj, int id, int compId){
		for (ZltjjgEntity entity : zltj){
			if (entity.getCpid() == id && entity.getDwid() == compId){
				return entity;
			}
		}
		return null;
	}
	
	@Override
	public ZltjjgEntity getByDateTotal(Date d, List<Integer> cplist,
			Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		if (null == dateTotal){
	        
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="from ZltjjgEntity where nf = :nf and dwid = :dwid";
//			}else{
//				sql ="from ZltjjgEntity where nf = :nf and dwid = :dwid";
//			}
			Query q = getEntityManager().createQuery(sql);
			q.setParameter("nf", ec.getYear());
			q.setParameter("dwid", company.getId());
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			dateTotal = q.getResultList();
		}
		
		ZltjjgEntity ret = new ZltjjgEntity();
		for (ZltjjgEntity entity : dateTotal){
			if (entity.getYf() == ec.getMonth() && cplist.contains(entity.getCpid())){
				ret.setBhgs(MathUtil.sum(entity.getBhgs(), ret.getBhgs()));
				ret.setZs(MathUtil.sum(entity.getZs(), ret.getZs()));
			}
		}
		
		return ret;
	}

	
	
	@Override
	public ZltjjgEntity getByDate(Date d, int cpid, Company company, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(d);
		if (null == byDate){
	       // Query q = getEntityManager().createQuery("from ZltjjgEntity where zt in :zt and nf = :nf and yf = :yf and dwid in :dwid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="from ZltjjgEntity where nf = :nf and yf = :yf and dwid in :dwid";
//			}else{
//				sql ="from ZltjjgEntity where nf = :nf and yf = :yf and dwid in :dwid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwid", comps);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			byDate = q.getResultList();
		}
		
		return find(byDate, cpid, company.getId());
	}



	@Override
	public ZltjjgEntity getYearAcc(Date d, int cpid, Company company, List<Integer> zts) {
		if (null == yearAcc){
			EasyCalendar ec = new EasyCalendar(d);
	        //Query q = getEntityManager().createQuery("select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and  nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwid group by dwid, cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwid group by dwid, cpid";
//			}else{
//				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwid group by dwid, cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwid", comps);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			yearAcc = new ArrayList<ZltjjgEntity>();
			if (!ret.isEmpty()){
				for (Object[] objs : ret){
					ZltjjgEntity entity = new ZltjjgEntity();
					entity.setDwid((Integer) objs[0]);
					entity.setCpid((Integer) objs[1]);
					entity.setBhgs(objs[2] == null ? null : ((Double)objs[2]));
					entity.setZs(objs[3] == null ? null : ((Double)objs[3]));
					yearAcc.add(entity);
				}
			}
		}

		return find(yearAcc, cpid, company.getId());
	}



	@Override
	public ZltjjgEntity getJdAcc(Date d, int cpid, Company company, List<Integer> zts) {
		if(jdAcc == null){
			jdAcc = new ArrayList<ZltjjgEntity>();
			EasyCalendar ec = new EasyCalendar(d);
	       // Query q = getEntityManager().createQuery("select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwid group by dwid, cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwid group by dwid, cpid";
//			}else{
//				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwid group by dwid, cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
	        
	        Integer jdStart = 1;
	        if (ec.getSeason() == 3){
	        	jdStart = 7;
	        }
	        
			q.setParameter("jdstart", jdStart);
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwid", comps);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			if (!ret.isEmpty()){
				for (Object[] objs : ret){
					ZltjjgEntity entity = new ZltjjgEntity();
					entity.setDwid((Integer) objs[0]);
					entity.setCpid((Integer) objs[1]);
					entity.setBhgs(objs[2] == null ? null : ((Double)objs[2]));
					entity.setZs(objs[3] == null ? null : ((Double)objs[3]));
					jdAcc.add(entity);
				}
			}
		}
		return find(jdAcc, cpid, company.getId());
	}



	@Override
	public ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company, List<Integer> zts) {
		if (jdAccQntq == null){
			jdAccQntq = new ArrayList<ZltjjgEntity>();
			EasyCalendar ec = new EasyCalendar(d);
			ec.addYear(-1);
	        //Query q = getEntityManager().createQuery("select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where zt in :zt and nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwid group by dwid, cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwid group by dwid, cpid";
//			}else{
//				sql ="select dwid, cpid, sum(bhgs) as bhgs, sum(zs) as zs from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwid group by dwid, cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
	        Integer jdStart = 1;
	        if (ec.getSeason() == 3){
	        	jdStart = 7;
	        }
	        
			q.setParameter("jdstart", jdStart);
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwid", comps);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			if (!ret.isEmpty()){
				for (Object[] objs : ret){
					ZltjjgEntity entity = new ZltjjgEntity();
					entity.setDwid((Integer) objs[0]);
					entity.setCpid((Integer) objs[1]);
					entity.setBhgs(objs[2] == null ? null : ((Double)objs[2]));
					entity.setZs(objs[3] == null ? null : ((Double)objs[3]));
					jdAccQntq.add(entity);
				}
			}
		}
		return find(jdAccQntq, cpid, company.getId());
	}

	@Override
	public ZltjjgEntity getByDateIgnoreStatus(Date d, Integer cpid,
			Company company) {
		return this.stubDao.getByDateIgnoreStatus(d, cpid, company);
	}

	@Override
	public ZltjjgEntity getByDate(Date d, int id, List<Integer> ids,
			List<Integer> zts) {
		
		return this.stubDao.getByDate(d, id, ids, zts);
	}

	@Override
	public ZltjjgEntity getYearAcc(Date d, int id, List<Integer> ids,
			List<Integer> zts) {
		String key = "YearAcc" + id;
		if (this.cache == null){
			cache = new HashMap<String, ZltjjgEntity>();
		}
		if (!cache.containsKey(key)){
			EasyCalendar ec = new EasyCalendar(d);
	      //  Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where zt  in :zt and nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwids group by cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwids group by cpid";
//			}else{
//				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= 1 and yf <= :yf  and dwid in :dwids group by cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwids", ids);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			for (Object[] row : ret){
				ZltjjgEntity entity = new ZltjjgEntity();
				if (row[0] != null){
					entity.setBhgs(((Double)row[0]));
				}
				if (row[1] != null){
					entity.setZs(((Double)row[1]));
				}
				entity.setCpid((Integer)row[2]);
				cache.put("YearAcc" + row[2], entity);
			}
		}
		
		
		return cache.get(key);
	}

	@Override
	public ZltjjgEntity getJdAcc(Date d, int id, List<Integer> ids, List<Integer> zts) {
		
		String key = "JdAcc" + id;
		if (this.cache == null){
			cache = new HashMap<String, ZltjjgEntity>();
		}
		if (!cache.containsKey(key)){
			EasyCalendar ec = new EasyCalendar(d);
	        //Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where zt  in :zt and nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwids group by cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwids group by cpid";
//			}else{
//				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf and dwid in :dwids group by cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
	        
	        Integer jdStart = 1;
	        if (ec.getSeason() == 3){
	        	jdStart = 7;
	        }
	        
			q.setParameter("jdstart", jdStart);
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwids", ids);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			for (Object[] row : ret){
				ZltjjgEntity entity = new ZltjjgEntity();
				if (row[0] != null){
					entity.setBhgs(((Double)row[0]));
				}
				if (row[1] != null){
					entity.setZs(((Double)row[1]));
				}
				entity.setCpid((Integer)row[2]);
				cache.put("JdAcc" + row[2], entity);
			}
		}
		
		
		return cache.get(key);
	}

	@Override
	public ZltjjgEntity getJdAccQntq(Date d, int id, List<Integer> ids,
			List<Integer> zts) {
		String key = "JdAccQntq" + id;
		if (this.cache == null){
			cache = new HashMap<String, ZltjjgEntity>();
		}
		if (!cache.containsKey(key)){
			EasyCalendar ec = new EasyCalendar(d);
			ec.addYear(-1);
	        //Query q = getEntityManager().createQuery("select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwids and zt  in :zt group by cpid");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwids group by cpid";
//			}else{
//				sql ="select sum(bhgs) as bhgs, sum(zs) as zs, cpid from ZltjjgEntity where nf = :nf and yf >= :jdstart and yf <= :yf  and dwid in :dwids group by cpid";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
			
	        Integer jdStart = 1;
	        if (ec.getSeason() == 3){
	        	jdStart = 7;
	        }
	        q.setParameter("jdstart", jdStart);
			q.setParameter("yf", ec.getMonth());
			q.setParameter("dwids", ids);
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			List<Object[]> ret = q.getResultList();
			for (Object[] row : ret){
				ZltjjgEntity entity = new ZltjjgEntity();
				if (row[0] != null){
					entity.setBhgs(((Double)row[0]));
				}
				if (row[1] != null){
					entity.setZs(((Double)row[1]));
				}
				entity.setCpid((Integer)row[2]);
				cache.put("JdAccQntq" + row[2], entity);
			}
		}
		
		
		return cache.get(key);
	}

	@Override
	public ZltjjgEntity getByDateTotal(Date date, List<Integer> cpids,
			List<Integer> ids, List<Integer> zts) {
		EasyCalendar ec = new EasyCalendar(date);
		if (null == dateTotal){
	        //Query q = getEntityManager().createQuery("from ZltjjgEntity where zt in :zt and nf = :nf and dwid in :dwids");
	        String sql = null;
//			if (zt != ZBStatus.NONE){
				sql ="from ZltjjgEntity where nf = :nf and dwid in :dwids";
//			}else{
//				sql ="from ZltjjgEntity where nf = :nf and dwid in :dwids";
//			}
			Query q = getEntityManager().createQuery(sql);
	        q.setParameter("nf", ec.getYear());
//			if (zt != ZBStatus.NONE){
//				q.setParameter("zt", zts);
//			}
			q.setParameter("dwids", ids);
			dateTotal = q.getResultList();
		}
		
		ZltjjgEntity ret = new ZltjjgEntity();
		for (ZltjjgEntity entity : dateTotal){
			if (entity.getYf() == ec.getMonth() && cpids.contains(entity.getCpid())){
				ret.setBhgs(MathUtil.sum(entity.getBhgs(), ret.getBhgs()));
				ret.setZs(MathUtil.sum(entity.getZs(), ret.getZs()));
			}
		}
		
		return ret;
	}

	@Override
	public List<ZltjjgEntity> getByDateIgnoreStatus(Date d, Company company) {
		return this.stubDao.getByDateIgnoreStatus(d, company);
	}
}
