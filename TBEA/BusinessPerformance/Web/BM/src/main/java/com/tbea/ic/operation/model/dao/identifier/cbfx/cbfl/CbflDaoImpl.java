package com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl;


import com.tbea.ic.operation.model.entity.identifier.cbfx.CbflEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl.CbflDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CbflDaoImpl.NAME)
@Transactional("transactionManager")
public class CbflDaoImpl extends AbstractReadWriteDaoImpl<CbflEntity> implements CbflDao {
	public final static String NAME = "CbflDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
