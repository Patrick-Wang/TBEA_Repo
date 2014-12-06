package com.tbea.test.testWebProject.model.dao.yszkpzjh;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.YSZKPZGH;

@Repository
@Transactional("transactionManager")
public class YSZKPZJHDaoImpl extends AbstractReadWriteDaoImpl<YSZKPZGH> implements YSZKPZJHDao{

	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<YSZKPZGH> getPzjhData(Date d, Company comp) {
		Query q = this.getEntityManager().createQuery("select y from YSZKPZGH y where y.yf = :date and y.qybh = :comp");
		q.setParameter("date", Util.format(d));
		q.setParameter("comp", comp.getId());
		return q.getResultList();
	}

}
