package com.tbea.test.testWebProject.service.yqysysfx;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.dao.yqysysfx.YQYSYSFXDao;
import com.tbea.test.testWebProject.model.entity.YQYSYSFX;
@Service
@Transactional("transactionManager")
public class YQYSYSFXServiceImpl implements YQYSYSFXService{

	@Autowired
	private YQYSYSFXDao ysysfxDao;
	
	public String[][] getYqysysfxData(Date d, Company comp){
		String[][] result = new String[4][7];
		List<YQYSYSFX> list = ysysfxDao.getYqysysfxList(d);
		int col = 0;
		for(YQYSYSFX ysys : list){
			if (comp.getId() == ysys.getQybh()){
				col = ysys.getYsfl();
				result[0][col - 1] = ysys.getHs() + "";
				result[1][col - 1] = ysys.getJe() + "";
				result[2][col - 1] = ysys.getFlsdhs() + "";
				result[3][col - 1] = ysys.getFlsdje() + "";
			}
		}
		return result;
	}

}
