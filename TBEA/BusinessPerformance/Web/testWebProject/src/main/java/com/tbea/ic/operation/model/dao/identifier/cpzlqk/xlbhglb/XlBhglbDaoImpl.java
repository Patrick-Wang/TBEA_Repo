package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlBhglbEntity;



@Repository(XlBhglbDaoImpl.NAME)
@Transactional("transactionManager")
public class XlBhglbDaoImpl extends AbstractReadWriteDaoImpl<XlBhglbEntity> implements XlBhglbDao {
	public final static String NAME = "XlBhglbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<XlBhglbEntity> getAll() {
		 Query q = getEntityManager().createQuery("from XlBhglbEntity");
		return q.getResultList();
	}
	
	@Override
	public XlBhglbEntity getByName(String name) {
		 Query q = getEntityManager().createQuery("from XlBhglbEntity where name=:name");
		 q.setParameter("name", name);
		 List<XlBhglbEntity> ret = q.getResultList();
		 if (ret.isEmpty()){
			 return null;
		 }
		return ret.get(0);
	}
}
