package com.tbea.datatransfer.service.inner.yqkqsbhb;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.yqkqsbhb.YQKQSBHBDao;
import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.entity.inner.YQKQSBHB;

@Transactional("transactionManager")
public class YQKQSBHBServiceImpl implements YQKQSBHBService {

	private YQKQSBHBDao yqkqsbhbDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private static String[] QYBHArray = { "1", "2", "3", "301", "4", "5", "6" };

	private void importYQKByQY(String baseMonth) throws Exception {
		YQKQSBHB yqkqsbhb = null;
		Map<String, Double> yq1yynMap = yszktzLocalDao.getYQKByQY(baseMonth, 0,
				1);
		Map<String, Double> yq1_3yMap = yszktzLocalDao.getYQKByQY(baseMonth, 1,
				3);
		Map<String, Double> yq3_6yMap = yszktzLocalDao.getYQKByQY(baseMonth, 3,
				6);
		Map<String, Double> yq6_12yMap = yszktzLocalDao.getYQKByQY(baseMonth,
				6, 12);
		Map<String, Double> yq1nysMap = yszktzLocalDao.getYQKByQY(baseMonth,
				12, null);
		for (String qybh : QYBHArray) {
			yqkqsbhb = new YQKQSBHB();
			yqkqsbhb.setNy(baseMonth);
			yqkqsbhb.setYq1yyn(yq1yynMap.get(qybh));
			yqkqsbhb.setYq1_3y(yq1_3yMap.get(qybh));
			yqkqsbhb.setYq3_6y(yq3_6yMap.get(qybh));
			yqkqsbhb.setYq6_12y(yq6_12yMap.get(qybh));
			yqkqsbhb.setYq1nys(yq1nysMap.get(qybh));
			yqkqsbhb.setQybh(Integer.valueOf(qybh));
			yqkqsbhbDao.merge(yqkqsbhb);
		}
	}

	@Override
	public boolean importYQKQSBHB() {
		boolean result = false;
		try {
			SimpleDateFormat nyFormat = new SimpleDateFormat("yyyyMM");
			String baseMonth = nyFormat.format(new Date(System
					.currentTimeMillis()));
			YQKQSBHB yqkqsbhb = new YQKQSBHB();
			yqkqsbhb.setNy(baseMonth);
			yqkqsbhb.setYq1yyn(yszktzLocalDao.getYQK(baseMonth, 0, 1));
			yqkqsbhb.setYq1_3y(yszktzLocalDao.getYQK(baseMonth, 1, 3));
			yqkqsbhb.setYq3_6y(yszktzLocalDao.getYQK(baseMonth, 3, 6));
			yqkqsbhb.setYq6_12y(yszktzLocalDao.getYQK(baseMonth, 6, 12));
			yqkqsbhb.setYq1nys(yszktzLocalDao.getYQK(baseMonth, 12, null));
			yqkqsbhb.setQybh(9999);
			yqkqsbhbDao.merge(yqkqsbhb);
			importYQKByQY(baseMonth);

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YQKQSBHBDao getYqkqsbhbDao() {
		return yqkqsbhbDao;
	}

	public void setYqkqsbhbDao(YQKQSBHBDao yqkqsbhbDao) {
		this.yqkqsbhbDao = yqkqsbhbDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
