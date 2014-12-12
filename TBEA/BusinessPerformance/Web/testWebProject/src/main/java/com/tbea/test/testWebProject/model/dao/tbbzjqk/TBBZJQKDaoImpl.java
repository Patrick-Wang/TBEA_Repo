package com.tbea.test.testWebProject.model.dao.tbbzjqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.TBBZJXX;

@Repository
@Transactional("transactionManager")
public class TBBZJQKDaoImpl extends AbstractReadWriteDaoImpl<TBBZJXX> implements TBBZJQKDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	public List<TBBZJXX> getTbbzj(Date d, Company comp){
		Query q = getEntityManager().createQuery("select t from TBBZJXX t where t.qybh = :compId and t.nf = :year and t.yf <= :month");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		q.setParameter("compId", comp.getId());
		q.setParameter("year", c.get(Calendar.YEAR));
		q.setParameter("month", c.get(Calendar.MONTH) + 1);
		return q.getResultList();
	}

}
