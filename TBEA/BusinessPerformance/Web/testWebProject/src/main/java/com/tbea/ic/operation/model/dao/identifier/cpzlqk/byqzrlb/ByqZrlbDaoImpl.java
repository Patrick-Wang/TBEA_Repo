package com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqzrlb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqZrlbEntity;



@Repository(ByqZrlbDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqZrlbDaoImpl extends AbstractReadWriteDaoImpl<ByqZrlbEntity> implements ByqZrlbDao {
	public final static String NAME = "ByqZrlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<ByqZrlbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from ByqZrlbEntity");
		return q.getResultList();
	}
	
	@Override
	public ByqZrlbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from ByqZrlbEntity where name=:name");
		 q.setParameter("name", name);
		 List<ByqZrlbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
