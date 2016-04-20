package com.tbea.ic.operation.model.dao.identifier.ylfx.clmc;


import com.tbea.ic.operation.model.entity.identifier.ylfx.ClmcEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.ylfx.clmc.ClmcDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ClmcDaoImpl.NAME)
@Transactional("transactionManager")
public class ClmcDaoImpl extends AbstractReadWriteDaoImpl<ClmcEntity> implements ClmcDao {
	public final static String NAME = "ClmcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
