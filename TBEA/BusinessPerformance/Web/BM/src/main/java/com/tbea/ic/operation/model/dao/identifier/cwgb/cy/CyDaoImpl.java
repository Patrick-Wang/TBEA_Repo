package com.tbea.ic.operation.model.dao.identifier.cwgb.cy;


import com.tbea.ic.operation.model.entity.identifier.cwgb.CyEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CyDaoImpl.NAME)
@Transactional("transactionManager")
public class CyDaoImpl extends AbstractReadWriteDaoImpl<CyEntity> implements CyDao {
	public final static String NAME = "CyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
