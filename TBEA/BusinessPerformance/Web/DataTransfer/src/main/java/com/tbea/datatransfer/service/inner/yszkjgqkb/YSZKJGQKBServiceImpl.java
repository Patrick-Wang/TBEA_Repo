package com.tbea.datatransfer.service.inner.yszkjgqkb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.inner.yszkjgqkb.YSZKJGQKBDao;
import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.entity.inner.YSZKJGQKB;

@Transactional("transactionManager")
public class YSZKJGQKBServiceImpl implements YSZKJGQKBService {

	private YSZKJGQKBDao yszkjgqkbDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private static String[] QYBHArray = { "1", "2", "3", "301", "4", "5", "6" };

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
		YSZKJGQKB yszkjgqkb = null;
		Double ysje = null;
		Double ysjezj = null;
		for (String qybh : QYBHArray) {
			ysje = ysjeMap.get(qybh);
			ysjezj = ysjezjMap.get(qybh);
			yszkjgqkb = new YSZKJGQKB();
			yszkjgqkb.setNy(baseMonth);
			yszkjgqkb.setHy(hyName);
			yszkjgqkb.setYsje(ysje);
			yszkjgqkb.setZqbbl(CommonMethod.getPercent(ysje, ysjezj));
			yszkjgqkb.setYq1yyn(yq1yynMap.get(qybh));
			yszkjgqkb.setYq1_3y(yq1_3yMap.get(qybh));
			yszkjgqkb.setYq3_6y(yq3_6yMap.get(qybh));
			yszkjgqkb.setYq6_12y(yq6_12yMap.get(qybh));
			yszkjgqkb.setYq1nys(yq1nysMap.get(qybh));
			yszkjgqkb.setWdqk(wdqkMap.get(qybh));
			yszkjgqkb.setWdqzbj(wdqzbjMap.get(qybh));
			yszkjgqkb.setYszkhj(ysje);
			// yszkjg.setYszkhj(yszktzLocalDao.getYSZKJG(baseMonth, null, null,
			// sshyList,
			// isIncluded, isTotal, false, true));
			yszkjgqkb.setQybh(Integer.valueOf(qybh));
			yszkjgqkbDao.merge(yszkjgqkb);
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
		YSZKJGQKB yszkjgqkb = new YSZKJGQKB();
		yszkjgqkb.setNy(baseMonth);
		yszkjgqkb.setHy(hyName);
		yszkjgqkb.setYsje(ysje);
		yszkjgqkb.setZqbbl(CommonMethod.getPercent(ysje, ysjezj));
		yszkjgqkb.setYq1yyn(yszktzLocalDao.getYSZKJG(baseMonth, 0, 1, sshyList,
				isIncluded, isTotal, false, false));
		yszkjgqkb.setYq1_3y(yszktzLocalDao.getYSZKJG(baseMonth, 1, 3, sshyList,
				isIncluded, isTotal, false, false));
		yszkjgqkb.setYq3_6y(yszktzLocalDao.getYSZKJG(baseMonth, 3, 6, sshyList,
				isIncluded, isTotal, false, false));
		yszkjgqkb.setYq6_12y(yszktzLocalDao.getYSZKJG(baseMonth, 6, 12,
				sshyList, isIncluded, isTotal, false, false));
		yszkjgqkb.setYq1nys(yszktzLocalDao.getYSZKJG(baseMonth, 12, null,
				sshyList, isIncluded, isTotal, false, false));
		yszkjgqkb.setWdqk(yszktzLocalDao.getYSZKJG(baseMonth, null, 0,
				sshyList, isIncluded, isTotal, true, false));
		yszkjgqkb.setWdqzbj(yszktzLocalDao.getYSZKJG(baseMonth, null, 0,
				sshyList, isIncluded, isTotal, true, true));
		yszkjgqkb.setYszkhj(ysje);
		// yszkjg.setYszkhj(yszktzLocalDao.getYSZKJG(baseMonth, null, null,
		// sshyList,
		// isIncluded, isTotal, false, true));
		yszkjgqkb.setQybh(9999);
		yszkjgqkbDao.merge(yszkjgqkb);
		importYSZKJGByHYAndQY(baseMonth, hyName, sshyList, isIncluded, isTotal);
		return;
	}

	@Override
	public boolean importYSZKJGQKB() {
		boolean result = false;
		try {
//			 yszkjgqkbDao.truncateYSZKJGQKB();
			SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");
			String baseMonth = null;
			Calendar cur = Calendar.getInstance();
//			 Calendar end = Calendar.getInstance();
//			 cur.set(2013, 1 - 1, 1);
//			 end.setTimeInMillis(System.currentTimeMillis());
			cur.setTimeInMillis(System.currentTimeMillis());
//			 while (!cur.after(end)) {
			baseMonth = month_sdf.format(cur.getTime());
			List<String> sshyGWList = new ArrayList<String>();
			sshyGWList.add("01");
			importYSZKJGByHY(baseMonth, "国网", sshyGWList, true, false);

			List<String> sshyNWList = new ArrayList<String>();
			sshyNWList.add("02");
			importYSZKJGByHY(baseMonth, "南网", sshyNWList, true, false);

			List<String> sshySSDLList = new ArrayList<String>();
			sshySSDLList.add("08");
			importYSZKJGByHY(baseMonth, "省、市电力系统", sshySSDLList, true, false);

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
//			 cur.add(cur.MONTH, 1);
//			 }
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("importYSZKJGQKB:" + result);
		}
		return result;
	}

	public YSZKJGQKBDao getYszkjgqkbDao() {
		return yszkjgqkbDao;
	}

	public void setYszkjgqkbDao(YSZKJGQKBDao yszkjgqkbDao) {
		this.yszkjgqkbDao = yszkjgqkbDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
