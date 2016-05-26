package com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs;


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
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfNdqcsEntity;
import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;



@Repository(YjsfNdqcsDaoImpl.NAME)
@Transactional("transactionManager")
public class YjsfNdqcsDaoImpl extends AbstractReadWriteDaoImpl<YjsfNdqcsEntity> implements YjsfNdqcsDao {
	public final static String NAME = "YjsfNdqcsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public YjsfNdqcsEntity getByDate(Date d, Company company, Integer sz) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Query q = this.getEntityManager().createQuery("from YjsfNdqcsEntity where nf=:nf and dwid=:compId and sz = :sz)");
		
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("sz", sz);
		q.setParameter("compId", company.getId());
		List<YjsfNdqcsEntity> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
