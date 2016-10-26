package com.tbea.ic.operation.model.dao.cpzlqk.yclhgl;


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
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZlYclhglEntity;



@Repository(ZlYclhglDaoImpl.NAME)
@Transactional("transactionManager")
public class ZlYclhglDaoImpl extends AbstractReadWriteDaoImpl<ZlYclhglEntity> implements ZlYclhglDao {
	public final static String NAME = "ZlYclhglDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	

	@Override
	public ZlYclhglEntity getFirst(Date d, Company company) {
		EasyCalendar ec = new EasyCalendar(d);
        Query q = getEntityManager().createQuery("from ZlYclhglEntity where nf = :nf and  yf = :yf and dwid = :dwid");
        q.setFirstResult(0);
        q.setMaxResults(1);
		q.setParameter("nf", ec.getYear());
		q.setParameter("yf", ec.getMonth());
		q.setParameter("dwid", company.getId());
		List<ZlYclhglEntity> result = q.getResultList();
		if (result.isEmpty()){
			return null;
		}
		return result.get(0);
	}


}
