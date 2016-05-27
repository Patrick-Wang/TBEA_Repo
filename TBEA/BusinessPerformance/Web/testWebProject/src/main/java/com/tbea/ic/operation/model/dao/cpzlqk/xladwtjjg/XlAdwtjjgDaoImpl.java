package com.tbea.ic.operation.model.dao.cpzlqk.xladwtjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XlAdwtjjgEntity;



@Repository(XlAdwtjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class XlAdwtjjgDaoImpl extends AbstractReadWriteDaoImpl<XlAdwtjjgEntity> implements XlAdwtjjgDao {
	public final static String NAME = "XlAdwtjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<XlAdwtjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from XlAdwtjjgEntity");
		return q.getResultList();
	}

	@Override
	public List<XlAdwtjjgEntity> getByDw(Company company) {
		 Query q = getEntityManager().createQuery("from XlAdwtjjgEntity where dw != null and dw.id = :dwid");
		 q.setParameter("dwid", company.getId());
		return q.getResultList();
	}
}
