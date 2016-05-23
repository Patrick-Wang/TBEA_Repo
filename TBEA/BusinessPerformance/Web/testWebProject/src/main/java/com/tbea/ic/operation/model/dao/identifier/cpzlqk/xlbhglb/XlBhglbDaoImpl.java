package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlBhglbEntity;



@Repository(XlBhglbDaoImpl.NAME)
@Transactional("transactionManager")
public class XlBhglbDaoImpl extends AbstractReadWriteDaoImpl<XlBhglbEntity> implements XlBhglbDao {
	public final static String NAME = "XlBhglbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
