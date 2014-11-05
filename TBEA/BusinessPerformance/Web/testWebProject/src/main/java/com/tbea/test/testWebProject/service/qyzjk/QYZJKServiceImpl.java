package com.tbea.test.testWebProject.service.qyzjk;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.qyzjk.QYZJKDao;
import com.tbea.test.testWebProject.model.entity.QYZJK;

//@Service
@Transactional("transactionManager2")
public class QYZJKServiceImpl implements QYZJKService {

//	@Autowired
	private QYZJKDao qyzjkDao;

	@Override
	public QYZJK getQYZJKById(int id) {
		return qyzjkDao.getById(id);
	}

	public QYZJKDao getQyzjkDao() {
		return qyzjkDao;
	}

	public void setQyzjkDao(QYZJKDao qyzjkDao) {
		this.qyzjkDao = qyzjkDao;
	}

}
