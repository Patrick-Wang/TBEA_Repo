package com.tbea.datatransfer.service.local.fkfs.byq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQGWLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.fkfs.FKFSBYQGWBYQDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQGWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQGWBYQ;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class FKFSBYQGWTransferServiceImpl implements FKFSBYQGWTransferService {

	private FKFSBYQGWLocalDao fkfsbyqgwLocalDao;

	private FKFSBYQGWBYQDao fkfsbyqgwTBDao;

	private FKFSBYQGWBYQDao fkfsbyqgwSBDao;

	private FKFSBYQGWBYQDao fkfsbyqgwXBDao;

	private static SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");

	private static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private void transferFKFSBYQGWByZJB(int qybh,
			List<FKFSBYQGWBYQ> fkfsbyqgwList) {
		Date gxrq = null;
		fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(qybh);
		FKFSBYQGWLocal fkfsbyqgwLocal = null;
		for (FKFSBYQGWBYQ fkfsbyqgw : fkfsbyqgwList) {
			fkfsbyqgwLocal = new FKFSBYQGWLocal();
			gxrq = fkfsbyqgw.getGxrq();
			fkfsbyqgwLocal.setGxrq(gxrq);
			fkfsbyqgwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqgwLocal.setGsbm(fkfsbyqgw.getGsbm());
			fkfsbyqgwLocal.setGwhtddzlbs(fkfsbyqgw.getGwhtddzlbs());
			fkfsbyqgwLocal.setGwhtddzlje(fkfsbyqgw.getGwhtddzlje());
			fkfsbyqgwLocal.setN3_4_2_1bs(fkfsbyqgw.getN3_4_2_1bs());
			fkfsbyqgwLocal.setN3_4_2_1je(fkfsbyqgw.getN3_4_2_1je());
			fkfsbyqgwLocal.setN3_4_2d5_0d5bs(fkfsbyqgw.getN3_4_2d5_0d5bs());
			fkfsbyqgwLocal.setN3_4_2d5_0d5je(fkfsbyqgw.getN3_4_2d5_0d5je());
			fkfsbyqgwLocal.setN0_9_0_1bs(fkfsbyqgw.getN0_9_0_1bs());
			fkfsbyqgwLocal.setN0_9_0_1je(fkfsbyqgw.getN0_9_0_1je());
			fkfsbyqgwLocal.setN1_4_4_1bs(fkfsbyqgw.getN1_4_4_1bs());
			fkfsbyqgwLocal.setN1_4_4_1je(fkfsbyqgw.getN1_4_4_1je());
			fkfsbyqgwLocal.setN1_4_4d5_0d5bs(fkfsbyqgw.getN1_4_4d5_0d5bs());
			fkfsbyqgwLocal.setN1_4_4d5_0d5je(fkfsbyqgw.getN1_4_4d5_0d5je());
			fkfsbyqgwLocal.setN0_10_0_0bs(fkfsbyqgw.getN0_10_0_0bs());
			fkfsbyqgwLocal.setN0_10_0_0je(fkfsbyqgw.getN0_10_0_0je());
			fkfsbyqgwLocal.setN9d5_0d5bs(fkfsbyqgw.getN9d5_0d5bs());
			fkfsbyqgwLocal.setN9d5_0d5je(fkfsbyqgw.getN9d5_0d5je());
			fkfsbyqgwLocal.setQtbs(fkfsbyqgw.getQtbs());
			fkfsbyqgwLocal.setQtje(fkfsbyqgw.getQtje());
			fkfsbyqgwLocal.setSfdrwc(fkfsbyqgw.getSfdrwc());
			fkfsbyqgwLocal.setQybh(qybh);
			fkfsbyqgwLocalDao.merge(fkfsbyqgwLocal);
		}
	}

	private void transferFKFSBYQGWByWS(int qybh, String userid,
			String password, String scheme) {

		fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(qybh);
		WebServiceClient webServiceClient = new WebServiceClient();
		List<Map<String, Object>> recList = webServiceClient.getRec(userid,
				password, scheme);
		FKFSBYQGWLocal fkfsbyqgwLocal = null;
		Date gxrq = null;
		for (Map<String, Object> recMap : recList) {
			fkfsbyqgwLocal = new FKFSBYQGWLocal();
			gxrq = CommonMethod.objectToDate(timeFormat, recMap.get("gxrq"));
			fkfsbyqgwLocal.setGxrq(gxrq);
			fkfsbyqgwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqgwLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
			fkfsbyqgwLocal.setGwhtddzlbs(Integer.valueOf(String.valueOf(recMap
					.get("gwhtddzlbs"))));
			fkfsbyqgwLocal.setGwhtddzlje(CommonMethod.objectToDouble(recMap
					.get("gwhtddzlje")));
			fkfsbyqgwLocal.setN3_4_2_1bs(Integer.valueOf(String.valueOf(recMap
					.get("3_4_2_1bs"))));
			fkfsbyqgwLocal.setN3_4_2_1je(CommonMethod.objectToDouble(recMap
					.get("3_4_2_1je")));
			fkfsbyqgwLocal.setN3_4_2d5_0d5bs(Integer.valueOf(String
					.valueOf(recMap.get("3_4_2d5_0d5bs"))));
			fkfsbyqgwLocal.setN3_4_2d5_0d5je(CommonMethod.objectToDouble(recMap
					.get("3_4_2d5_0d5je")));
			fkfsbyqgwLocal.setN0_9_0_1bs(Integer.valueOf(String.valueOf(recMap
					.get("0_9_0_1bs"))));
			fkfsbyqgwLocal.setN0_9_0_1je(CommonMethod.objectToDouble(recMap
					.get("0_9_0_1je")));
			fkfsbyqgwLocal.setN1_4_4_1bs(Integer.valueOf(String.valueOf(recMap
					.get("1_4_4_1bs"))));
			fkfsbyqgwLocal.setN1_4_4_1je(CommonMethod.objectToDouble(recMap
					.get("1_4_4_1je")));
			fkfsbyqgwLocal.setN1_4_4d5_0d5bs(Integer.valueOf(String
					.valueOf(recMap.get("1_4_4d5_0d5bs"))));
			fkfsbyqgwLocal.setN1_4_4d5_0d5je(CommonMethod.objectToDouble(recMap
					.get("1_4_4d5_0d5je")));
			fkfsbyqgwLocal.setN0_10_0_0bs(Integer.valueOf(String.valueOf(recMap
					.get("0_10_0_0bs"))));
			fkfsbyqgwLocal.setN0_10_0_0je(CommonMethod.objectToDouble(recMap
					.get("0_10_0_0je")));
			fkfsbyqgwLocal.setN9d5_0d5bs(Integer.valueOf(String.valueOf(recMap
					.get("9d5_0d5bs"))));
			fkfsbyqgwLocal.setN9d5_0d5je(CommonMethod.objectToDouble(recMap
					.get("9d5_0d5je")));
			fkfsbyqgwLocal.setQtbs(CommonMethod.objectToInteger(recMap
					.get("qtbs")));
			fkfsbyqgwLocal.setQtje(CommonMethod.objectToDouble(recMap
					.get("qtje")));
			fkfsbyqgwLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
			fkfsbyqgwLocal.setQybh(qybh);
			fkfsbyqgwLocalDao.merge(fkfsbyqgwLocal);
		}
	}

	@Override
	public boolean transferFKFSBYQGW() {
		boolean result = false;
		boolean sbResult = false;
		boolean hbResult = false;
		boolean xbResult = false;
		boolean tbResult = false;
		// sb
		try {
			List<FKFSBYQGWBYQ> fkfsbyqgwSBList = fkfsbyqgwSBDao
					.getAllFKFSBYQGW();
			transferFKFSBYQGWByZJB(1, fkfsbyqgwSBList);
			sbResult = true;
		} catch (Exception e) {
			sbResult = false;
		}
		// hb
		try {
			transferFKFSBYQGWByWS(2, "web_test", "123456",
					"yszk_ws_htfkfstj_byq_gwfk");
			hbResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			hbResult = false;
		}
		// xb
		try {
			List<FKFSBYQGWBYQ> fkfsbyqgwXBList = fkfsbyqgwXBDao
					.getAllFKFSBYQGW();
			transferFKFSBYQGWByZJB(3, fkfsbyqgwXBList);
			xbResult = true;
		} catch (Exception e) {
			xbResult = false;
		}
		// tb
		try {
			List<FKFSBYQGWBYQ> fkfsbyqgwTBList = fkfsbyqgwTBDao
					.getAllFKFSBYQGW();
			transferFKFSBYQGWByZJB(301, fkfsbyqgwTBList);
			tbResult = true;
		} catch (Exception e) {
			tbResult = false;
		}

		if (sbResult && hbResult && xbResult && tbResult) {
			result = true;
			System.out.println("transferFKFSBYQGW:true");
		} else {
			result = false;
			System.out.println("transferFKFSBYQGWsb:" + sbResult);
			System.out.println("transferFKFSBYQGWhb:" + hbResult);
			System.out.println("transferFKFSBYQGWxb:" + xbResult);
			System.out.println("transferFKFSBYQGWtb:" + tbResult);
		}
		return result;
	}

	public FKFSBYQGWLocalDao getFkfsbyqgwLocalDao() {
		return fkfsbyqgwLocalDao;
	}

	public void setFkfsbyqgwLocalDao(FKFSBYQGWLocalDao fkfsbyqgwLocalDao) {
		this.fkfsbyqgwLocalDao = fkfsbyqgwLocalDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwTBDao() {
		return fkfsbyqgwTBDao;
	}

	public void setFkfsbyqgwTBDao(FKFSBYQGWBYQDao fkfsbyqgwTBDao) {
		this.fkfsbyqgwTBDao = fkfsbyqgwTBDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwSBDao() {
		return fkfsbyqgwSBDao;
	}

	public void setFkfsbyqgwSBDao(FKFSBYQGWBYQDao fkfsbyqgwSBDao) {
		this.fkfsbyqgwSBDao = fkfsbyqgwSBDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwXBDao() {
		return fkfsbyqgwXBDao;
	}

	public void setFkfsbyqgwXBDao(FKFSBYQGWBYQDao fkfsbyqgwXBDao) {
		this.fkfsbyqgwXBDao = fkfsbyqgwXBDao;
	}

}
