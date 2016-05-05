package com.tbea.ic.operation.model.dao.cwgb.cpdlmlb;


import com.tbea.ic.operation.model.entity.cwgb.CpdlmlbEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.cpdlmlb.CpdlmlbDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CpdlmlbDaoImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlbDaoImpl extends AbstractReadWriteDaoImpl<CpdlmlbEntity> implements CpdlmlbDao {
	public final static String NAME = "CpdlmlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
