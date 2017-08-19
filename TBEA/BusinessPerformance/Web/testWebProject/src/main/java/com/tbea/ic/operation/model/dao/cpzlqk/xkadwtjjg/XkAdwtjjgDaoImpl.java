package com.tbea.ic.operation.model.dao.cpzlqk.xkadwtjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XkAdwtjjgEntity;



@Repository(XkAdwtjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class XkAdwtjjgDaoImpl extends AbstractReadWriteDaoImpl<XkAdwtjjgEntity> implements XkAdwtjjgDao {
	public final static String NAME = "XkAdwtjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<XkAdwtjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from XkAdwtjjgEntity");
		return q.getResultList();
	}

	@Override
	public List<XkAdwtjjgEntity> getByDw(Company company) {
		Query q = getEntityManager().createQuery("from XkAdwtjjgEntity where dwid=:dwid");
		q.setParameter("dwid", company.getId());
		return q.getResultList();
	}
}
