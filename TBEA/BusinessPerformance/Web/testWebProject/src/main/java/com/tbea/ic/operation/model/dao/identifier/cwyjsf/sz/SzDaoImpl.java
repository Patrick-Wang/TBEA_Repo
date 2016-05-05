package com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz;


import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz.SzDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(SzDaoImpl.NAME)
@Transactional("transactionManager")
public class SzDaoImpl extends AbstractReadWriteDaoImpl<SzEntity> implements SzDao {
	public final static String NAME = "SzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
