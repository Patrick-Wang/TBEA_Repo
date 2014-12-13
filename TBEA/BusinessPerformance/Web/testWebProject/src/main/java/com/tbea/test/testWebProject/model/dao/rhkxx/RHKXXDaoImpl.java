package com.tbea.test.testWebProject.model.dao.rhkxx;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.RHKXX;
import com.tbea.test.testWebProject.model.entity.ZTYSZKFX;

@Repository
@Transactional("transactionManager")
public class RHKXXDaoImpl  extends AbstractReadWriteDaoImpl<RHKXX> implements RHKXXDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<RHKXX> getRhkxxData(Date d) {
		Query q = this.getEntityManager().createQuery("from RHKXX where DateDiff(dd, Hkrq, :date) = 0");
		q.setParameter("date", d);
		return q.getResultList();
	}

}
