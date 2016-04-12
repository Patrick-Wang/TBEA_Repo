package com.tbea.ic.operation.model.dao.chgb.chjykc;


import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChJykcDaoImpl.NAME)
@Transactional("transactionManager")
public class ChJykcDaoImpl extends AbstractReadWriteDaoImpl<ChJykcEntity> implements ChJykcDao {
	public final static String NAME = "ChJykcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
