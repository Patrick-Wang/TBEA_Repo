package com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml;


import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CpdlmlDaoImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlDaoImpl extends AbstractReadWriteDaoImpl<CpdlmlEntity> implements CpdlmlDao {
	public final static String NAME = "CpdlmlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
