package com.tbea.test.testWebProject.model.dao.hkjhzxqk;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.hkjhzxqk.HKJHZXQKDao;
import com.tbea.test.testWebProject.model.entity.HKJHJG;
import com.tbea.test.testWebProject.model.entity.QYZJK;
import com.tbea.test.testWebProject.model.entity.YDHKJHJG;
import com.tbea.test.testWebProject.model.entity.YDSJHKQK;


@Repository
@Transactional("transactionManager")
public class HKJHZXQKDaoImpl  extends AbstractReadWriteDaoImpl<QYZJK> implements HKJHZXQKDao{


	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	

	@Override
	public List<YDSJHKQK> getSjhkqk(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select h from YDSJHKQK h where h.qybh = :compId and h.gxrq >= :start and h.gxrq < :end");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM"); 
		String dstr = formatter.format(d);
		Date dStart = Date.valueOf(dstr + "-1");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		dstr = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-1";
		Date dEnd =  Date.valueOf(dstr);//String.format(format, args)c.get(field)
		int id = Integer.valueOf(comp.getId());
		q.setParameter("compId", id);
		q.setParameter("start", dStart);
		q.setParameter("end", dEnd);
		return q.getResultList();
	}



	@Override
	public List<YDHKJHJG> getHkjhjg(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select h from YDHKJHJG h where h.qybh = :compId and h.gxrq >= :start and h.gxrq < :end");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM"); 
		String dstr = formatter.format(d);
		Date dStart = Date.valueOf(dstr + "-1");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		dstr = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-1";
		Date dEnd =  Date.valueOf(dstr);//String.format(format, args)c.get(field)
		int id = Integer.valueOf(comp.getId());
		q.setParameter("compId", id);
		q.setParameter("start", dStart);
		q.setParameter("end", dEnd);
		return q.getResultList();
	}

}
