package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyZBXXDaoImpl extends AbstractReadWriteDaoImpl<ZBXX> implements ZzyZBXXDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ZBXX> getZbs(String zbidstrs) {
		Query q = this.getEntityManager().createQuery("from ZBXX where id in ( " + zbidstrs + " )");
		return q.getResultList();
	}	
}
