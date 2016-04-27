package com.tbea.ic.operation.model.dao.identifier.scfxgb.hy;


import com.tbea.ic.operation.model.entity.identifier.scfxgb.HyEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.scfxgb.hy.HyDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(HyDaoImpl.NAME)
@Transactional("transactionManager")
public class HyDaoImpl extends AbstractReadWriteDaoImpl<HyEntity> implements HyDao {
	public final static String NAME = "HyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
