package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xkzrlb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XkZrlbEntity;



@Repository(XkZrlbDaoImpl.NAME)
@Transactional("transactionManager")
public class XkZrlbDaoImpl extends AbstractReadWriteDaoImpl<XkZrlbEntity> implements XkZrlbDao {
	public final static String NAME = "XkZrlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<XkZrlbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from XkZrlbEntity");
		return q.getResultList();
	}
	
	@Override
	public XkZrlbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from XkZrlbEntity where name=:name");
		 q.setParameter("name", name);
		 List<XkZrlbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
