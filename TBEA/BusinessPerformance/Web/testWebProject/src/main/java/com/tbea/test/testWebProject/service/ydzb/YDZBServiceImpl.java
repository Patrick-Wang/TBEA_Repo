package com.tbea.test.testWebProject.service.ydzb;

import java.util.Calendar;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.qyzjk.QYZJKDao;
import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDao;
import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDaoImpl;
import com.tbea.test.testWebProject.model.dao.yqk.YQKDaoImpl;
import com.tbea.test.testWebProject.model.entity.QYZJK;

@Service
@Transactional("transactionManager2")
public class YDZBServiceImpl implements  YDZBService {

	@Autowired
	private YDZBDao ydzbDao;

	@Override
	public String[][] getYDZB(Calendar cal) {
		ydzbDao.getYDZB(cal);
		return null;
	}



}
