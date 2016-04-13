package com.tbea.ic.operation.model.dao.chgb.chzm;


import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChZmDaoImpl.NAME)
@Transactional("transactionManager")
public class ChZmDaoImpl extends AbstractReadWriteDaoImpl<ChZmEntity> implements ChZmDao {
	public final static String NAME = "ChZmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
