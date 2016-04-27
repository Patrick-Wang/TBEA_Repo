package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;



@Repository(XfcpqyDaoImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyDaoImpl extends AbstractReadWriteDaoImpl<XfcpqyEntity> implements XfcpqyDao {
	public final static String NAME = "XfcpqyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XfcpqyEntity> getByDate(Date date, Date d, Company company,
			int cp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from XfcpqyEntity where nf=:nf and yf=:yf and dwid=:compId and cpid=:cpid");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		q.setParameter("cpid", cp);
		List<XfcpqyEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return (List<XfcpqyEntity>) list.get(0);
	}
}
