package com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys;


import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YqyszcsysDaoImpl.NAME)
@Transactional("transactionManager")
public class YqyszcsysDaoImpl extends AbstractReadWriteDaoImpl<YqyszcsysEntity> implements YqyszcsysDao {
	public final static String NAME = "YqyszcsysDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
