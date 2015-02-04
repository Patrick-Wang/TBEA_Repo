package com.tbea.datatransfer.service.local.fkfs.byq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQNWLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.fkfs.FKFSBYQNWBYQDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class FKFSBYQNWTransferServiceImpl implements FKFSBYQNWTransferService {

	private FKFSBYQNWLocalDao fkfsbyqnwLocalDao;

	private FKFSBYQNWBYQDao fkfsbyqnwTBDao;

	private FKFSBYQNWBYQDao fkfsbyqnwSBDao;

	private FKFSBYQNWBYQDao fkfsbyqnwXBDao;

	private static SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");

	private static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private void transferFKFSBYQNWByZJB(int qybh,
			List<FKFSBYQNWBYQ> fkfsbyqnwList) {
		Date gxrq = null;
		fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(qybh);
		FKFSBYQNWLocal fkfsbyqnwLocal = null;
		for (FKFSBYQNWBYQ fkfsbyqnw : fkfsbyqnwList) {
			fkfsbyqnwLocal = new FKFSBYQNWLocal();
			gxrq = fkfsbyqnw.getGxrq();
			fkfsbyqnwLocal.setGxrq(gxrq);
			fkfsbyqnwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqnwLocal.setGsbm(fkfsbyqnw.getGsbm());
			fkfsbyqnwLocal.setNwhtddzlbs(fkfsbyqnw.getNwhtddzlbs());
			fkfsbyqnwLocal.setNwhtddzlje(fkfsbyqnw.getNwhtddzlje());
			fkfsbyqnwLocal.setN3_3_3_1bs(fkfsbyqnw.getN3_3_3_1bs());
			fkfsbyqnwLocal.setN3_3_3_1je(fkfsbyqnw.getN3_3_3_1je());
			fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(fkfsbyqnw.getN1_4_4_0d5_0d5bs());
			fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(fkfsbyqnw.getN1_4_4_0d5_0d5je());
			fkfsbyqnwLocal.setN1_2_6d5_0d5bs(fkfsbyqnw.getN1_2_6d5_0d5bs());
			fkfsbyqnwLocal.setN1_2_6d5_0d5je(fkfsbyqnw.getN1_2_6d5_0d5je());
			fkfsbyqnwLocal.setN1_4_4d5_0d5bs(fkfsbyqnw.getN1_4_4d5_0d5bs());
			fkfsbyqnwLocal.setN1_4_4d5_0d5je(fkfsbyqnw.getN1_4_4d5_0d5je());
			fkfsbyqnwLocal.setQtybs(fkfsbyqnw.getQtybs());
			fkfsbyqnwLocal.setQtyje(fkfsbyqnw.getQtyje());
			fkfsbyqnwLocal.setQtebs(fkfsbyqnw.getQtebs());
			fkfsbyqnwLocal.setQteje(fkfsbyqnw.getQteje());
			fkfsbyqnwLocal.setSfdrwc(fkfsbyqnw.getSfdrwc());
			fkfsbyqnwLocal.setQybh(qybh);
			fkfsbyqnwLocalDao.merge(fkfsbyqnwLocal);
		}
	}

	private void transferFKFSBYQNWByWS(int qybh, String userid,
			String password, String scheme) {

		Date gxrq = null;
		fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(qybh);
		WebServiceClient webServiceClient = new WebServiceClient();
		List<Map<String, Object>> recList = webServiceClient.getRec(userid,
				password, scheme);
		FKFSBYQNWLocal fkfsbyqnwLocal = null;
		for (Map<String, Object> recMap : recList) {
			fkfsbyqnwLocal = new FKFSBYQNWLocal();
			gxrq = CommonMethod.objectToDate(timeFormat, recMap.get("gxrq"));
			fkfsbyqnwLocal.setGxrq(gxrq);
			fkfsbyqnwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqnwLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
			fkfsbyqnwLocal.setNwhtddzlbs(CommonMethod.objectToInteger(String
					.valueOf(recMap.get("nwhtddzlbs"))));
			fkfsbyqnwLocal.setNwhtddzlje(CommonMethod.objectToDouble(recMap
					.get("nwhtddzlje")));
			fkfsbyqnwLocal.setN3_3_3_1bs(CommonMethod.objectToInteger(String
					.valueOf(recMap.get("3_3_3_1bs"))));
			fkfsbyqnwLocal.setN3_3_3_1je(CommonMethod.objectToDouble(recMap
					.get("3_3_3_1je")));
			fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(CommonMethod
					.objectToInteger(String.valueOf(recMap
							.get("1_4_4_0d5_0d5bs"))));
			fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(CommonMethod
					.objectToDouble(recMap.get("1_4_4_0d5_0d5je")));
			fkfsbyqnwLocal
					.setN1_2_6d5_0d5bs(CommonMethod.objectToInteger(String
							.valueOf(recMap.get("1_2_6d5_0d5bs"))));
			fkfsbyqnwLocal.setN1_2_6d5_0d5je(CommonMethod.objectToDouble(recMap
					.get("1_2_6d5_0d5je")));
			fkfsbyqnwLocal
					.setN1_4_4d5_0d5bs(CommonMethod.objectToInteger(String
							.valueOf(recMap.get("1_4_4d5_0d5bs"))));
			fkfsbyqnwLocal.setN1_4_4d5_0d5je(CommonMethod.objectToDouble(recMap
					.get("1_4_4d5_0d5je")));
			fkfsbyqnwLocal.setQtybs(CommonMethod.objectToInteger(recMap
					.get("qtybs")));
			fkfsbyqnwLocal.setQtyje(CommonMethod.objectToDouble(recMap
					.get("qtyje")));
			fkfsbyqnwLocal.setQtebs(CommonMethod.objectToInteger(recMap
					.get("qtebs")));
			fkfsbyqnwLocal.setQteje(CommonMethod.objectToDouble(recMap
					.get("qteje")));
			fkfsbyqnwLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
			fkfsbyqnwLocal.setQybh(qybh);
			fkfsbyqnwLocalDao.merge(fkfsbyqnwLocal);
		}
	}

	@Override
	public boolean transferFKFSBYQNW() {
		boolean result = false;
		boolean sbResult = false;
		boolean hbResult = false;
		boolean xbResult = false;
		boolean tbResult = false;
		// sb
		try {
			List<FKFSBYQNWBYQ> fkfsbyqnwSBList = fkfsbyqnwSBDao
					.getAllFKFSBYQNW();
			transferFKFSBYQNWByZJB(1, fkfsbyqnwSBList);
			sbResult = true;
		} catch (Exception e) {
			sbResult = false;
		}
		// hb
		try {
			transferFKFSBYQNWByWS(2, "web_test", "123456",
					"yszk_ws_htfkfstj_byq_nwfk");
			hbResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			hbResult = false;
		}
		// xb
		try {
			List<FKFSBYQNWBYQ> fkfsbyqnwXBList = fkfsbyqnwXBDao
					.getAllFKFSBYQNW();
			transferFKFSBYQNWByZJB(3, fkfsbyqnwXBList);
			xbResult = true;
		} catch (Exception e) {
			xbResult = false;
		}
		// tb
		try {
			List<FKFSBYQNWBYQ> fkfsbyqnwTBList = fkfsbyqnwTBDao
					.getAllFKFSBYQNW();
			transferFKFSBYQNWByZJB(301, fkfsbyqnwTBList);
			tbResult = true;
		} catch (Exception e) {
			tbResult = false;
		}

		if (sbResult && hbResult && xbResult && tbResult) {
			result = true;
			System.out.println("transferFKFSBYQNW:true");
		} else {
			result = false;
			System.out.println("transferFKFSBYQNWsb:" + sbResult);
			System.out.println("transferFKFSBYQNWhb:" + hbResult);
			System.out.println("transferFKFSBYQNWxb:" + xbResult);
			System.out.println("transferFKFSBYQNWtb:" + tbResult);
		}
		try {
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferFKFSBYQNW:" + result);
		}
		return result;
	}

	public FKFSBYQNWLocalDao getFkfsbyqnwLocalDao() {
		return fkfsbyqnwLocalDao;
	}

	public void setFkfsbyqnwLocalDao(FKFSBYQNWLocalDao fkfsbyqnwLocalDao) {
		this.fkfsbyqnwLocalDao = fkfsbyqnwLocalDao;
	}

	public FKFSBYQNWBYQDao getFkfsbyqnwTBDao() {
		return fkfsbyqnwTBDao;
	}

	public void setFkfsbyqnwTBDao(FKFSBYQNWBYQDao fkfsbyqnwTBDao) {
		this.fkfsbyqnwTBDao = fkfsbyqnwTBDao;
	}

	public FKFSBYQNWBYQDao getFkfsbyqnwSBDao() {
		return fkfsbyqnwSBDao;
	}

	public void setFkfsbyqnwSBDao(FKFSBYQNWBYQDao fkfsbyqnwSBDao) {
		this.fkfsbyqnwSBDao = fkfsbyqnwSBDao;
	}

	public FKFSBYQNWBYQDao getFkfsbyqnwXBDao() {
		return fkfsbyqnwXBDao;
	}

	public void setFkfsbyqnwXBDao(FKFSBYQNWBYQDao fkfsbyqnwXBDao) {
		this.fkfsbyqnwXBDao = fkfsbyqnwXBDao;
	}

}
