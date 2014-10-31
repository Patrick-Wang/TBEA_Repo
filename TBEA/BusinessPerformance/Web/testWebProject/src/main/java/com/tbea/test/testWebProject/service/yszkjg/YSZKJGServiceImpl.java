package com.tbea.test.testWebProject.service.yszkjg;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.dao.yszkjg.YSZKJGDao;
import com.tbea.test.testWebProject.model.entity.local.YSZKJG;

@Transactional("transactionManager")
public class YSZKJGServiceImpl implements YSZKJGService {

	private YSZKJGDao yszkjgDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private void importYSZKJGByHY(String baseMonth, String hyName,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		Double ysjezj = yszktzLocalDao.getYSZKJE(baseMonth, null, false, true);
		Double ysje = yszktzLocalDao.getYSZKJE(baseMonth, sshyList, isIncluded,
				isTotal);
		YSZKJG yszkjg = new YSZKJG();
		yszkjg.setNy("201410");
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
		yszkjg.setYszkhj(yszktzLocalDao.getYSZKJG(baseMonth, null, 0, sshyList,
				isIncluded, isTotal, false, true));
		yszkjgDao.merge(yszkjg);
		return;
	}

	@Override
	public boolean importYSZKJG() {
		boolean result = false;
		try {
			String baseMonth = "201410";
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
