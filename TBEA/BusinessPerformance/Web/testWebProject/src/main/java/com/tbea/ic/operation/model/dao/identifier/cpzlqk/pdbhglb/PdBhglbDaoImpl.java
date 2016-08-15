package com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdBhglbEntity;



@Repository(PdBhglbDaoImpl.NAME)
@Transactional("transactionManager")
public class PdBhglbDaoImpl extends AbstractReadWriteDaoImpl<PdBhglbEntity> implements PdBhglbDao {
	public final static String NAME = "PdBhglbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<PdBhglbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from PdBhglbEntity");
		return q.getResultList();
	}
	
	@Override
	public PdBhglbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from PdBhglbEntity where name=:name");
		 q.setParameter("name", name);
		 List<PdBhglbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
