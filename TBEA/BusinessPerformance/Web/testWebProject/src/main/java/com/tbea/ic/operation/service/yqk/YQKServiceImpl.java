package com.tbea.ic.operation.service.yqk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.ic.operation.model.dao.yqk.YQKDao;
import com.tbea.ic.operation.model.entity.local.YQK;

@Transactional("transactionManager")
public class YQKServiceImpl implements YQKService {

	private YQKDao yqkDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private static String[] QYBHArray = { "5", "6", "7", "8", "9", "10", "11" };

	private void importYQKByQY(String baseMonth) throws Exception {
		YQK yqk = null;
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
			yqk = new YQK();
			yqk.setNy(baseMonth);
			yqk.setYq1yyn(yq1yynMap.get(qybh));
			yqk.setYq1_3y(yq1_3yMap.get(qybh));
			yqk.setYq3_6y(yq3_6yMap.get(qybh));
			yqk.setYq6_12y(yq6_12yMap.get(qybh));
			yqk.setYq1nys(yq1nysMap.get(qybh));
			yqk.setQybh(Integer.valueOf(qybh));
			yqkDao.merge(yqk);
		}
	}

	@Override
	public boolean importYQK() {
		boolean result = false;
		try {
			List<String> monthList = new ArrayList<String>();
			monthList.add("201301");
			monthList.add("201302");
			monthList.add("201303");
			monthList.add("201304");
			monthList.add("201305");
			monthList.add("201306");
			monthList.add("201307");
			monthList.add("201308");
			monthList.add("201309");
			monthList.add("201310");
			monthList.add("201311");
			monthList.add("201312");
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
			for (String baseMonth : monthList) {
				YQK yqk = new YQK();
				yqk.setNy(baseMonth);
				yqk.setYq1yyn(yszktzLocalDao.getYQK(baseMonth, 0, 1));
				yqk.setYq1_3y(yszktzLocalDao.getYQK(baseMonth, 1, 3));
				yqk.setYq3_6y(yszktzLocalDao.getYQK(baseMonth, 3, 6));
				yqk.setYq6_12y(yszktzLocalDao.getYQK(baseMonth, 6, 12));
				yqk.setYq1nys(yszktzLocalDao.getYQK(baseMonth, 12, null));
				yqk.setQybh(9999);
				yqkDao.merge(yqk);
				importYQKByQY(baseMonth);
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
