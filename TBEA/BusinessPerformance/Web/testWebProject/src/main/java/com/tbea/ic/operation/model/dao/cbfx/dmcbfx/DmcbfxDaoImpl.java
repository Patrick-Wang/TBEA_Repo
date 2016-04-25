package com.tbea.ic.operation.model.dao.cbfx.dmcbfx;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cbfx.DmcbfxEntity;
import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(DmcbfxDaoImpl.NAME)
@Transactional("transactionManager")
public class DmcbfxDaoImpl extends AbstractReadWriteDaoImpl<DmcbfxEntity> implements DmcbfxDao {
	public final static String NAME = "DmcbfxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<DmcbfxEntity> getByDate(Date d, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Query q = this.getEntityManager().createQuery("from DmcbfxEntity where nf=:nf and yf=:yf and dwid=:compId");
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("compId", company.getId());
		List<ChxzqkEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
