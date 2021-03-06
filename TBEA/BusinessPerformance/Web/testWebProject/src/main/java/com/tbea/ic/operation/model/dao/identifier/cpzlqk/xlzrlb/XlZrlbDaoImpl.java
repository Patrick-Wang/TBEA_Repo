package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlZrlbEntity;



@Repository(XlZrlbDaoImpl.NAME)
@Transactional("transactionManager")
public class XlZrlbDaoImpl extends AbstractReadWriteDaoImpl<XlZrlbEntity> implements XlZrlbDao {
	public final static String NAME = "XlZrlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<XlZrlbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from XlZrlbEntity");
		return q.getResultList();
	}
	
	@Override
	public XlZrlbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from XlZrlbEntity where name=:name");
		 q.setParameter("name", name);
		 List<XlZrlbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
