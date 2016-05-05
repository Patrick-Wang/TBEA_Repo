package com.tbea.ic.operation.model.dao.cwgb.yjsf;


import com.tbea.ic.operation.model.entity.cwgb.YjsfEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.yjsf.YjsfDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YjsfDaoImpl.NAME)
@Transactional("transactionManager")
public class YjsfDaoImpl extends AbstractReadWriteDaoImpl<YjsfEntity> implements YjsfDao {
	public final static String NAME = "YjsfDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
