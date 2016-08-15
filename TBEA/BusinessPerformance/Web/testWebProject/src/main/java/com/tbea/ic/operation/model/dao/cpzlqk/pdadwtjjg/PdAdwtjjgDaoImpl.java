package com.tbea.ic.operation.model.dao.cpzlqk.pdadwtjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.PdAdwtjjgEntity;



@Repository(PdAdwtjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class PdAdwtjjgDaoImpl extends AbstractReadWriteDaoImpl<PdAdwtjjgEntity> implements PdAdwtjjgDao {
	public final static String NAME = "PdAdwtjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<PdAdwtjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from PdAdwtjjgEntity");
		return q.getResultList();
	}

	@Override
	public List<PdAdwtjjgEntity> getByDw(Company company) {
		Query q = getEntityManager().createQuery("from PdAdwtjjgEntity where dwid=:dwid");
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}
}
