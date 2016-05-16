package com.tbea.ic.operation.model.dao.cpzlqkyd.tjjg;


import com.tbea.ic.operation.model.entity.cpzlqkyd.TjjgEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqkyd.tjjg.TjjgDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(TjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class TjjgDaoImpl extends AbstractReadWriteDaoImpl<TjjgEntity> implements TjjgDao {
	public final static String NAME = "TjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
