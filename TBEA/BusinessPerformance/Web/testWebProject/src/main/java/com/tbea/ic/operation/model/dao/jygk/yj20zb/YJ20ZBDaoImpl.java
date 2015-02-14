package com.tbea.ic.operation.model.dao.jygk.yj20zb;

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
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
@Repository
@Transactional("transactionManager")
public class YJ20ZBDaoImpl extends AbstractReadWriteDaoImpl<YJ20ZB> implements YJ20ZBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public YJ20ZB getZb(Integer zb, Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where zbxx.id = :id and nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		List<YJ20ZB> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public List<YJ20ZB> getZbs(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company.getId());
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getApprovedZbs(Date date, Company comp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf = :nf and yf = :yf and yj20shzt.id = 1 and dwxx.id = :comp");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", comp.getId());
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getUnapprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf = :nf and yf = :yf and yj20shzt.id = 2 and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getZb(List<Company> comps, Date dStart, Date dEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf >= :nStart and nf <= :nEnd and yf >= :yStart and yf <= :yEnd and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nStart", calStart.get(Calendar.YEAR));
		q.setParameter("nEnd", calEnd.get(Calendar.YEAR));
		q.setParameter("yStart", calStart.get(Calendar.MONTH) + 1);
		q.setParameter("yEnd", calEnd.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getZb(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf = :nf and yf = :yf and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getApprovedZbs(Date date, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery("from YJ20ZB where nf = :nf and yf = :yf and yj20shzt.id = 1 and dwxx.id in ("+ Util.toBMString(comps) +")");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}
	@Override
	public List<Integer> getCompanies() {
		Query q = this.getEntityManager().createQuery("select dwxx.id from YJ20ZB");
		return q.getResultList();
	}

	@Override
	public List<YJ20ZB> getYj20zbs(List<YDZBZT> yd20zbzts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YJ28ZB> getYj28zbs(List<YDZBZT> yd28zbzts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SJZB> getSjzbs(List<YDZBZT> sjzbzts) {
		// TODO Auto-generated method stub
		return null;
	}

}
