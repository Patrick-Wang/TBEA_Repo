package com.tbea.ic.operation.model.dao.chgb.nych;


import com.tbea.ic.operation.model.entity.chgb.NychEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NychDaoImpl.NAME)
@Transactional("transactionManager")
public class NychDaoImpl extends AbstractReadWriteDaoImpl<NychEntity> implements NychDao {
	public final static String NAME = "NychDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
