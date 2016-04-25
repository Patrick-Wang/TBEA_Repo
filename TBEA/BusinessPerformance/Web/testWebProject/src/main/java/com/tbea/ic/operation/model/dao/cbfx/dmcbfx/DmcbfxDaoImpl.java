package com.tbea.ic.operation.model.dao.cbfx.dmcbfx;


import com.tbea.ic.operation.model.entity.cbfx.DmcbfxEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(DmcbfxDaoImpl.NAME)
@Transactional("transactionManager")
public class DmcbfxDaoImpl extends AbstractReadWriteDaoImpl<DmcbfxEntity> implements DmcbfxDao {
	public final static String NAME = "DmcbfxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
