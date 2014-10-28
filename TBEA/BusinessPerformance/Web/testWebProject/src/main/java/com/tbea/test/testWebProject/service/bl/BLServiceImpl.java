package com.tbea.test.testWebProject.service.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.bl.BLDao;
import com.tbea.test.testWebProject.model.entity.BL;

@Service
@Transactional("transactionManager2")
public class BLServiceImpl implements BLService {

	@Autowired
	private BLDao blDao;

	@Override
	public BL getBLById(int id) {
		return blDao.getById(id);
	}

}
