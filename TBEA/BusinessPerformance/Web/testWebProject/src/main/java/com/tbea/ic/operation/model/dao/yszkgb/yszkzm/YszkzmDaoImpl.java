package com.tbea.ic.operation.model.dao.yszkgb.yszkzm;


import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkzmDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkzmDaoImpl extends AbstractReadWriteDaoImpl<YszkzmEntity> implements YszkzmDao {
	public final static String NAME = "YszkzmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
