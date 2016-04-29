package com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.dwxxrefclmc;


import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.wgcpqk.yclbfqk.DwxxRefClmcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.dwxxrefclmc.DwxxRefClmcDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(DwxxRefClmcDaoImpl.NAME)
@Transactional("transactionManager")
public class DwxxRefClmcDaoImpl extends AbstractReadWriteDaoImpl<DwxxRefClmcEntity> implements DwxxRefClmcDao {
	public final static String NAME = "DwxxRefClmcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<DwxxRefClmcEntity> getByCompany(Company company) {
		Query q = this.getEntityManager().createQuery("from DwxxRefClmcEntity where dwid=:cpId");
		q.setParameter("cpId", company.getId());
		return q.getResultList();
	}
}
