package com.tbea.datatransfer.service.inner.cqk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.cqk.CQKDao;
import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.entity.inner.CQK;

@Transactional("transactionManager")
public class CQKServiceImpl implements CQKService {

	private CQKDao cqkDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private static String[] QYBHArray = { "1", "2", "3", "301", "4", "5", "6" };

	private void importCQKByHY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		// Total
		CQK cqk = new CQK();
		cqk.setNy(baseMonth);
		cqk.setHy(hyName);
		cqk.setCqk2n(yszktzLocalDao.getCQK(baseMonth, null, 730, sshyList,
				isIncluded, isTotal));
		cqk.setCqk3n(yszktzLocalDao.getCQK(baseMonth, 730, 1095, sshyList,
				isIncluded, isTotal));
		cqk.setCqk4njzq(yszktzLocalDao.getCQK(baseMonth, 1095, null, sshyList,
				isIncluded, isTotal));
		cqk.setZj(yszktzLocalDao.getCQK(baseMonth, null, null, sshyList,
				isIncluded, isTotal));
		cqk.setQybh(9999);
		cqkDao.merge(cqk);
		// QY
		importCQKByHYAndQY(baseMonth, hyName, sshyList, isIncluded, isTotal);
		return;
	}

	private void importCQKByHYAndQY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		CQK cqk = null;
		Map<String, Double> cqk2nMap = yszktzLocalDao.getCQKByQY(baseMonth,
				null, 730, sshyList, isIncluded, isTotal);
		Map<String, Double> cqk3nMap = yszktzLocalDao.getCQKByQY(baseMonth,
				730, 1095, sshyList, isIncluded, isTotal);
		Map<String, Double> cqk4njzqMap = yszktzLocalDao.getCQKByQY(baseMonth,
				1095, null, sshyList, isIncluded, isTotal);
		Map<String, Double> zjMap = yszktzLocalDao.getCQKByQY(baseMonth, null,
				null, sshyList, isIncluded, isTotal);
		for (String qybh : QYBHArray) {
			cqk = new CQK();
			cqk.setNy(baseMonth);
			cqk.setHy(hyName);
			cqk.setCqk2n(cqk2nMap.get(qybh));
			cqk.setCqk3n(cqk3nMap.get(qybh));
			cqk.setCqk4njzq(cqk4njzqMap.get(qybh));
			cqk.setZj(zjMap.get(qybh));
			cqk.setQybh(Integer.valueOf(qybh));
			cqkDao.merge(cqk);
		}
		return;
	}

	@Override
	public boolean importCQK() {
		boolean result = false;
		try {
			// cqkDao.truncateCQK();
			SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");
			String baseMonth = null;
			Calendar cur = Calendar.getInstance();
			// Calendar end = Calendar.getInstance();
			// cur.set(2013, 1 - 1, 1);
			// end.setTimeInMillis(System.currentTimeMillis());
			cur.setTimeInMillis(System.currentTimeMillis());
			// while (!cur.after(end)) {
			baseMonth = month_sdf.format(cur.getTime());
			List<String> sshyGWNWList = new ArrayList<String>();
			sshyGWNWList.add("01");
			sshyGWNWList.add("02");
			importCQKByHY(baseMonth, "国网、南网", sshyGWNWList, true, false);

			List<String> sshySSDLList = new ArrayList<String>();
			sshySSDLList.add("08");
			importCQKByHY(baseMonth, "省、市电力系统", sshySSDLList, true, false);

			List<String> sshyWDFDList = new ArrayList<String>();
			sshyWDFDList.add("03");
			sshyWDFDList.add("04");
			sshyWDFDList.add("05");
			sshyWDFDList.add("06");
			sshyWDFDList.add("07");
			importCQKByHY(baseMonth, "五大发电", sshyWDFDList, true, false);

			List<String> sshyQTDYList = new ArrayList<String>();
			sshyQTDYList.add("09");
			importCQKByHY(baseMonth, "其他电源", sshyQTDYList, true, false);

			List<String> sshySYSHList = new ArrayList<String>();
			sshySYSHList.add("16");
			importCQKByHY(baseMonth, "石油石化", sshySYSHList, true, false);

			List<String> sshyGDJTList = new ArrayList<String>();
			sshyGDJTList.add("14");
			sshyGDJTList.add("15");
			importCQKByHY(baseMonth, "轨道交通", sshyGDJTList, true, false);

			List<String> sshyCKHTList = new ArrayList<String>();
			sshyCKHTList.add("14");
			sshyCKHTList.add("15");
			importCQKByHY(baseMonth, "出口合同", sshyCKHTList, true, false);

			List<String> sshyQTList = new ArrayList<String>();
			sshyQTList.addAll(sshyGWNWList);
			sshyQTList.addAll(sshySSDLList);
			sshyQTList.addAll(sshyWDFDList);
			sshyQTList.addAll(sshyQTDYList);
			sshyQTList.addAll(sshySYSHList);
			sshyQTList.addAll(sshyGDJTList);
			sshyQTList.addAll(sshyCKHTList);
			importCQKByHY(baseMonth, "其他", sshyQTList, false, false);

			importCQKByHY(baseMonth, "合计", null, false, true);
			// cur.add(cur.MONTH, 1);
			// }
			result = true;
		} catch (Exception e) {
			result = false;
		} finally {
			System.out.println("importCQK:" + result);
		}
		return result;
	}

	public CQKDao getCqkDao() {
		return cqkDao;
	}

	public void setCqkDao(CQKDao cqkDao) {
		this.cqkDao = cqkDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
