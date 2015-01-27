package com.tbea.ic.operation.model.dao.testTable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.TestTable;

//@Repository
@Transactional("transactionManager")
public class TestTableDaoImpl extends AbstractReadWriteDaoImpl<TestTable>
		implements TestTableDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
