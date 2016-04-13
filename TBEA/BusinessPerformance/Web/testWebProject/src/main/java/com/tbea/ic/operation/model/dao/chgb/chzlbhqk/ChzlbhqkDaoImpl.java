package com.tbea.ic.operation.model.dao.chgb.chzlbhqk;


import com.tbea.ic.operation.model.entity.chgb.ChzlbhqkEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ChzlbhqkDaoImpl.NAME)
@Transactional("transactionManager")
public class ChzlbhqkDaoImpl extends AbstractReadWriteDaoImpl<ChzlbhqkEntity> implements ChzlbhqkDao {
	public final static String NAME = "ChzlbhqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
