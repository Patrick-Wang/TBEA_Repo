package com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm;


import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(JykcxmDaoImpl.NAME)
@Transactional("transactionManager")
public class JykcxmDaoImpl extends AbstractReadWriteDaoImpl<JykcxmEntity> implements JykcxmDao {
	public final static String NAME = "JykcxmDaoImpl";

	EntityManager manager;
	
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public int getXMAmount() {
		Query q = this.getEntityManager().createQuery("select count(*) from JykcxmEntity");
		return Integer.parseInt(q.getResultList().get(0).toString());
	}
	
	@Override
	public List<JykcxmEntity> getXMMapping() {
		Query q = this.getEntityManager().createQuery("from JykcxmEntity");
		return q.getResultList();
	}
}
