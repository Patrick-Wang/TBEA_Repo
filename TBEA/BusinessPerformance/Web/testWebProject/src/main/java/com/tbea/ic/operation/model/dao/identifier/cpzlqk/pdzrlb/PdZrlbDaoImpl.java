package com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdzrlb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdZrlbEntity;



@Repository(PdZrlbDaoImpl.NAME)
@Transactional("transactionManager")
public class PdZrlbDaoImpl extends AbstractReadWriteDaoImpl<PdZrlbEntity> implements PdZrlbDao {
	public final static String NAME = "PdZrlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<PdZrlbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from PdZrlbEntity");
		return q.getResultList();
	}
	
	@Override
	public PdZrlbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from PdZrlbEntity where name=:name");
		 q.setParameter("name", name);
		 List<PdZrlbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
