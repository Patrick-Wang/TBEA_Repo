package com.tbea.ic.operation.service.testTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.testTable.TestTableDao;
import com.tbea.ic.operation.model.entity.TestTable;

//@Service
@Transactional("transactionManager")
public class TestTableServiceImpl implements TestTableService {

//	@Autowired
	private TestTableDao testTableDao;

	@Override
	public TestTable getTestTableById(int id) {
		return testTableDao.getById(id);
	}

	public TestTableDao getTestTableDao() {
		return testTableDao;
	}

	public void setTestTableDao(TestTableDao testTableDao) {
		this.testTableDao = testTableDao;
	}

}
