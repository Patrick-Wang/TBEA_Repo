package com.tbea.test.testWebProject.service.yqk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.dao.yqk.YQKDao;
import com.tbea.test.testWebProject.model.entity.local.YQK;

@Transactional("transactionManager")
public class YQKServiceImpl implements YQKService {

	private YQKDao yqkDao;

	private YSZKTZLocalDao yszktzLocalDao;

	@Override
	public boolean importYQK() {
		boolean result = false;
		try {
			List<String> monthList = new ArrayList<String>();
			monthList.add("201401");
			monthList.add("201402");
			monthList.add("201403");
			monthList.add("201404");
			monthList.add("201405");
			monthList.add("201406");
			monthList.add("201407");
			monthList.add("201408");
			monthList.add("201409");
			monthList.add("201410");
			monthList.add("201411");
			monthList.add("201412");
			for (String month : monthList) {
				YQK yqk = new YQK();
				yqk.setNy(month);
				yqk.setYq1yyn(yszktzLocalDao.getYQK(month, 0, 1));
				yqk.setYq1_3y(yszktzLocalDao.getYQK(month, 1, 3));
				yqk.setYq3_6y(yszktzLocalDao.getYQK(month, 3, 6));
				yqk.setYq6_12y(yszktzLocalDao.getYQK(month, 6, 12));
				yqk.setYq1nys(yszktzLocalDao.getYQK(month, 12, null));
				yqkDao.merge(yqk);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YQKDao getYqkDao() {
		return yqkDao;
	}


	public void setYqkDao(YQKDao yqkDao) {
		this.yqkDao = yqkDao;
	}


	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
