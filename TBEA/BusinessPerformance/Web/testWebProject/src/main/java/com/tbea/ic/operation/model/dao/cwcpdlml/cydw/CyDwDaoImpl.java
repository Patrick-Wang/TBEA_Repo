package com.tbea.ic.operation.model.dao.cwcpdlml.cydw;


import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.CyDwEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.cwcpdlml.cydw.CyDwDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CyDwDaoImpl.NAME)
@Transactional("transactionManager")
public class CyDwDaoImpl extends AbstractReadWriteDaoImpl<CyDwEntity> implements CyDwDao {
	public final static String NAME = "CyDwDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public CyDwEntity getByDw(Company company) {
		Query q = this.getEntityManager().createQuery("from CyDwEntity where dw.id=:compId");
		q.setParameter("compId", company.getId());
		List<CyDwEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
