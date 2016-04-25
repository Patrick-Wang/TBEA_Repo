package com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx;


import com.tbea.ic.operation.model.entity.cbfx.NymyywmlfxEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NymyywmlfxDaoImpl.NAME)
@Transactional("transactionManager")
public class NymyywmlfxDaoImpl extends AbstractReadWriteDaoImpl<NymyywmlfxEntity> implements NymyywmlfxDao {
	public final static String NAME = "NymyywmlfxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
