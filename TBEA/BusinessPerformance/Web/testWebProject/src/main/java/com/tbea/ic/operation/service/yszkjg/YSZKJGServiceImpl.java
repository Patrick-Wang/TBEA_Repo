package com.tbea.ic.operation.service.yszkjg;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.ic.operation.model.dao.yszkjg.YSZKJGDao;
import com.tbea.ic.operation.model.entity.local.YSZKJG;

@Transactional("transactionManager")
public class YSZKJGServiceImpl implements YSZKJGService {

	private YSZKJGDao yszkjgDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private static String[] QYBHArray = { "5", "6", "7", "8", "9", "10", "11" };

	private void importYSZKJGByHYAndQY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		Map<String, Double> ysjezjMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				null, null, null, false, true, false, true);
		Map<String, Double> ysjeMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				null, null, sshyList, isIncluded, isTotal, false, true);
		// Double ysjezj = yszktzLocalDao.getYSZKJE(baseMonth, null, false,
		// true);
		// Double ysje = yszktzLocalDao.getYSZKJE(baseMonth, sshyList,
		// isIncluded,
		// isTotal);
		Map<String, Double> yq1yynMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				0, 1, sshyList, isIncluded, isTotal, false, false);
		Map<String, Double> yq1_3yMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				1, 3, sshyList, isIncluded, isTotal, false, false);
		Map<String, Double> yq3_6yMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				3, 6, sshyList, isIncluded, isTotal, false, false);
		Map<String, Double> yq6_12yMap = yszktzLocalDao.getYSZKJGByQY(
				baseMonth, 6, 12, sshyList, isIncluded, isTotal, false, false);
		Map<String, Double> yq1nysMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				12, null, sshyList, isIncluded, isTotal, false, false);
		Map<String, Double> wdqkMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				null, 0, sshyList, isIncluded, isTotal, true, false);
		Map<String, Double> wdqzbjMap = yszktzLocalDao.getYSZKJGByQY(baseMonth,
				null, 0, sshyList, isIncluded, isTotal, true, true);
		YSZKJG yszkjg = null;
		NumberFormat nf = new DecimalFormat("0.00%");
		Double ysje = null;
		Double ysjezj = null;
		for (String qybh : QYBHArray) {
			ysje = ysjeMap.get(qybh);
			ysjezj = ysjezjMap.get(qybh);
			yszkjg = new YSZKJG();
			yszkjg.setNy(baseMonth);
			yszkjg.setHy(hyName);
			yszkjg.setYsje(ysje);
			if (null != ysje && null != ysjezj) {
				yszkjg.setZqbbl(nf.format(ysje / ysjezj));
			} else {
				yszkjg.setZqbbl(null);
			}
			yszkjg.setYq1yyn(yq1yynMap.get(qybh));
			yszkjg.setYq1_3y(yq1_3yMap.get(qybh));
			yszkjg.setYq3_6y(yq3_6yMap.get(qybh));
			yszkjg.setYq6_12y(yq6_12yMap.get(qybh));
			yszkjg.setYq1nys(yq1nysMap.get(qybh));
			yszkjg.setWdqk(wdqkMap.get(qybh));
			yszkjg.setWdqzbj(wdqzbjMap.get(qybh));
			yszkjg.setYszkhj(ysje);
			// yszkjg.setYszkhj(yszktzLocalDao.getYSZKJG(baseMonth, null, null,
			// sshyList,
			// isIncluded, isTotal, false, true));
			yszkjg.setQybh(Integer.valueOf(qybh));
			yszkjgDao.merge(yszkjg);
		}
		return;
	}

	private void importYSZKJGByHY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		Double ysjezj = yszktzLocalDao.getYSZKJG(baseMonth, null, null, null,
				false, true, false, true);
		Double ysje = yszktzLocalDao.getYSZKJG(baseMonth, null, null, sshyList,
				isIncluded, isTotal, false, true);
		// Double ysjezj = yszktzLocalDao.getYSZKJE(baseMonth, null, false,
		// true);
		// Double ysje = yszktzLocalDao.getYSZKJE(baseMonth, sshyList,
		// isIncluded,
		// isTotal);
		YSZKJG yszkjg = new YSZKJG();
		yszkjg.setNy(baseMonth);
		yszkjg.setHy(hyName);
		yszkjg.setYsje(ysje);
		NumberFormat nf = new DecimalFormat("0.00%");
		yszkjg.setZqbbl(nf.format(ysje / ysjezj));
		yszkjg.setYq1yyn(yszktzLocalDao.getYSZKJG(baseMonth, 0, 1, sshyList,
				isIncluded, isTotal, false, false));
		yszkjg.setYq1_3y(yszktzLocalDao.getYSZKJG(baseMonth, 1, 3, sshyList,
				isIncluded, isTotal, false, false));
		yszkjg.setYq3_6y(yszktzLocalDao.getYSZKJG(baseMonth, 3, 6, sshyList,
				isIncluded, isTotal, false, false));
		yszkjg.setYq6_12y(yszktzLocalDao.getYSZKJG(baseMonth, 6, 12, sshyList,
				isIncluded, isTotal, false, false));
		yszkjg.setYq1nys(yszktzLocalDao.getYSZKJG(baseMonth, 12, null,
				sshyList, isIncluded, isTotal, false, false));
		yszkjg.setWdqk(yszktzLocalDao.getYSZKJG(baseMonth, null, 0, sshyList,
				isIncluded, isTotal, true, false));
		yszkjg.setWdqzbj(yszktzLocalDao.getYSZKJG(baseMonth, null, 0, sshyList,
				isIncluded, isTotal, true, true));
		yszkjg.setYszkhj(ysje);
		// yszkjg.setYszkhj(yszktzLocalDao.getYSZKJG(baseMonth, null, null,
		// sshyList,
		// isIncluded, isTotal, false, true));
		yszkjg.setQybh(9999);
		yszkjgDao.merge(yszkjg);
		importYSZKJGByHYAndQY(baseMonth, hyName, sshyList, isIncluded, isTotal);
		return;
	}

	@Override
	public boolean importYSZKJG() {
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
				List<String> sshyGWList = new ArrayList<String>();
				sshyGWList.add("01");
				importYSZKJGByHY(baseMonth, "国网", sshyGWList, true, false);

				List<String> sshyNWList = new ArrayList<String>();
				sshyNWList.add("02");
				importYSZKJGByHY(baseMonth, "南网", sshyNWList, true, false);

				List<String> sshySSDLList = new ArrayList<String>();
				sshySSDLList.add("08");
				importYSZKJGByHY(baseMonth, "省、市电力系统", sshySSDLList, true,
						false);

				List<String> sshyWDFDList = new ArrayList<String>();
				sshyWDFDList.add("03");
				sshyWDFDList.add("04");
				sshyWDFDList.add("05");
				sshyWDFDList.add("06");
				sshyWDFDList.add("07");
				importYSZKJGByHY(baseMonth, "五大发电", sshyWDFDList, true, false);

				List<String> sshyQTDYList = new ArrayList<String>();
				sshyQTDYList.add("09");
				importYSZKJGByHY(baseMonth, "其他电源", sshyQTDYList, true, false);

				List<String> sshyCKHTList = new ArrayList<String>();
				sshyCKHTList.add("14");
				sshyCKHTList.add("15");
				importYSZKJGByHY(baseMonth, "出口合同", sshyCKHTList, true, false);

				List<String> sshyQTList = new ArrayList<String>();
				sshyQTList.addAll(sshyGWList);
				sshyQTList.addAll(sshyNWList);
				sshyQTList.addAll(sshySSDLList);
				sshyQTList.addAll(sshyWDFDList);
				sshyQTList.addAll(sshyQTDYList);
				sshyQTList.addAll(sshyCKHTList);
				importYSZKJGByHY(baseMonth, "其他", sshyQTList, false, false);

				importYSZKJGByHY(baseMonth, "合计", null, false, true);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YSZKJGDao getYszkjgDao() {
		return yszkjgDao;
	}

	public void setYszkjgDao(YSZKJGDao yszkjgDao) {
		this.yszkjgDao = yszkjgDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
