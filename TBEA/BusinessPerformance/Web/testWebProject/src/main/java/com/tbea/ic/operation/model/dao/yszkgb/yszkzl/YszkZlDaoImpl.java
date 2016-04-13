package com.tbea.ic.operation.model.dao.yszkgb.yszkzl;


import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkZlDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkZlDaoImpl extends AbstractReadWriteDaoImpl<YszkZlEntity> implements YszkZlDao {
	public final static String NAME = "YszkZlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
