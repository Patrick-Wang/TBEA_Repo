package com.tbea.ic.operation.model.dao.chgb.chxzqk;


import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChxzqkDaoImpl.NAME)
@Transactional("transactionManager")
public class ChxzqkDaoImpl extends AbstractReadWriteDaoImpl<ChxzqkEntity> implements ChxzqkDao {
	public final static String NAME = "ChxzqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
