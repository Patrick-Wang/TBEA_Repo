package com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz;


import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkKxxzDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkKxxzDaoImpl extends AbstractReadWriteDaoImpl<YszkKxxzEntity> implements YszkKxxzDao {
	public final static String NAME = "YszkKxxzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
